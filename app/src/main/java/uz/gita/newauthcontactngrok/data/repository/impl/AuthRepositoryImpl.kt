package uz.gita.newauthcontactngrok.data.repository.impl

import uz.gita.contactngrockonlineandofline.data.shP.MyShP
import uz.gita.newauthcontactngrok.data.local.AppDatabase
import uz.gita.newauthcontactngrok.data.models.UserData
import uz.gita.newauthcontactngrok.data.remote.ContactClient
import uz.gita.newauthcontactngrok.data.repository.AuthRepository

class AuthRepositoryImpl : AuthRepository {
    private val authApi = ContactClient.getAuthApi()
    private val dao = AppDatabase.getInstance().contactDao()

    override suspend fun register(userData: UserData):Boolean {
        val response = authApi.register(userData)
        if (response.isSuccessful) {
            if (response.body() != null){
                val token:String = response.body()!!.data.token
                MyShP.getInstance().setToken(token)
                return true
            }
            return false
        }
        return false
    }

    override suspend fun unRegister(userData: UserData):Boolean {
        val response = authApi.unregister(userData)
        if (response.isSuccessful && response.body() != null) {
            dao.clear()
            return true
        }
        return false
    }

    override suspend fun login(userData: UserData):Boolean {
        val response = authApi.login(userData)
        if (response.isSuccessful && response.body() != null) {
            val token:String = response.body()!!.data.token
            MyShP.getInstance().setToken(token)
            return true
        }
        return false
    }
//-----------
    override suspend fun logout(userData: UserData) {
        MyShP.getInstance().clear()
    }
}