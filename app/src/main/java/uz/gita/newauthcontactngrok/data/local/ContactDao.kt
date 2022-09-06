package uz.gita.newauthcontactngrok.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import uz.gita.newauthcontactngrok.data.models.LocalData

@Dao
interface ContactDao {
    @Query("SELECT * FROM contact WHERE state = 0 OR state = 1 OR state = 2")
    fun getContacts(): LiveData<List<LocalData>>

    @Query("SELECT * FROM contact")
    fun getAllContacts():List<LocalData>

    @Query("DELETE FROM contact")
    suspend fun clear()

    @Query("SELECT * FROM contact WHERE localId = :id")
    suspend fun getContact(id: Int): LocalData

    @Delete
    suspend fun delete(localData: LocalData)

    @Insert
    suspend fun insert(localData: LocalData)

    @Insert
    suspend fun insert(list: List<LocalData>)

    @Update
    suspend fun update(localData: LocalData)

    @Transaction
    suspend fun updateWholeDB(newList: List<LocalData>){
        clear()
        insert(newList)
    }
}