package uz.gita.newauthcontactngrok.data.repository

import androidx.lifecycle.LiveData
import uz.gita.newauthcontactngrok.data.models.LocalData
import uz.gita.newauthcontactngrok.data.models.RequestData

interface ContactRepository {
    fun internetState(): Boolean

    fun getContacts(): LiveData<List<LocalData>>
    suspend fun clearShP()
    suspend fun clearLocalDB()
    suspend fun reLoadLocalData()
    suspend fun delete(id: Int, localId: Int)
    suspend fun putContact(id: Int, requestData: RequestData, localId: Int): Boolean
    suspend fun postContact(requestData: RequestData): Boolean
    suspend fun getContact(id: Int)
}