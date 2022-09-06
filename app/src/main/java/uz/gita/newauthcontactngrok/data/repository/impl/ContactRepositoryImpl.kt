package uz.gita.newauthcontactngrok.data.repository.impl

import android.util.Log
import androidx.lifecycle.LiveData
import uz.gita.contactngrockonlineandofline.data.shP.MyShP
import uz.gita.newauthcontactngrok.data.local.AppDatabase
import uz.gita.newauthcontactngrok.data.models.LocalData
import uz.gita.newauthcontactngrok.data.models.RequestData
import uz.gita.newauthcontactngrok.data.models.ResponseData
import uz.gita.newauthcontactngrok.data.remote.ContactClient
import uz.gita.newauthcontactngrok.data.repository.ContactRepository
import uz.gita.newauthcontactngrok.utils.hasConnection

class ContactRepositoryImpl : ContactRepository {

    private val dao = AppDatabase.getInstance().contactDao()
    private val contactApi = ContactClient.getContactApi()
    private val token = MyShP.getInstance().getToken()!!

    override suspend fun reLoadLocalData() {
        if (hasConnection()) {
            val list: List<LocalData> = dao.getAllContacts()
            list.forEach {
                when (it.state) {
                    1 -> {
                        postContact(RequestData(it.name, it.phone))
                    }
                    2 -> {
                        putContact(it.id, RequestData(it.name, it.phone), it.localId)
                    }
                    3 -> {
                        delete(it.id, it.localId)
                    }
                }
            }

            val response = contactApi.getContacts(token)
            if (response.isSuccessful) {
                val data = response.body()?.data!!
                dao.updateWholeDB(convert(data))
            }
        }
    }

    override fun internetState(): Boolean {
        return hasConnection()
    }

    override fun getContacts(): LiveData<List<LocalData>> {
        return dao.getContacts()
    }

    override suspend fun clearShP() {
        MyShP.getInstance().clear()
    }

    override suspend fun clearLocalDB() {
        dao.clear()
        AppDatabase.getInstance().clearAllTables()
    }

    override suspend fun delete(id: Int, localId: Int) {
        Log.d("begii","delete fun - id = $id, localId = $localId")
        if (hasConnection()) {
            val response = contactApi.delete(id, token)
            if (response.isSuccessful) {
                val data = response.body()?.data!!
                val localData = LocalData(data.id, data.name, data.phone, localId)
                dao.delete(localData)
            }
        } else {
            Log.d("begii","else ishladi--")
            val temData: LocalData = dao.getContact(localId)
            Log.d("begii","$temData")
            val data = LocalData(temData.id, temData.name, temData.phone, temData.localId, 3)
            dao.update(data)
        }

    }

    override suspend fun putContact(id: Int, requestData: RequestData, localId: Int): Boolean {
        if (hasConnection()) {
            val response = contactApi.putContact(token, id, requestData)
            if (response.isSuccessful) {
                val data = response.body()?.data!!
                val localData = LocalData(data.id, data.name, data.phone, localId)
                dao.update(localData)
                return true
            }
            return false
        } else {
            val temData: LocalData = dao.getContact(localId)
            val data = LocalData(temData.id, requestData.name, requestData.phone, temData.localId, 1)
            dao.update(data)
            return true
        }
    }

    override suspend fun postContact(requestData: RequestData): Boolean {
        if (hasConnection()) {
            val response = contactApi.postContact(token, requestData)
            if (response.isSuccessful) {
                val data = response.body()?.data!!
                val localData = LocalData(data.id, data.name, data.phone)
                dao.insert(localData)
                return true
            }
            return false
        } else {
            val data = LocalData(0, requestData.name, requestData.phone, 0, 1)
            dao.insert(data)
            return true
        }
    }

    override suspend fun getContact(id: Int) {
        val response = contactApi.getContact(id, token)
        if (response.isSuccessful) {
            dao.getContact(id)
        }
    }

    private fun convert(data: List<ResponseData>): List<LocalData> {
        val localDataList: ArrayList<LocalData> = ArrayList()
        data.forEach {
            localDataList.add(LocalData(it.id, it.name, it.phone))
        }
        return localDataList
    }

    private fun convert2(localData: List<LocalData>): List<RequestData> {
        return localData.map {
            RequestData(it.name, it.phone)
        }
    }
}