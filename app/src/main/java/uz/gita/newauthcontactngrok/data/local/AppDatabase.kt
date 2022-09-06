package uz.gita.newauthcontactngrok.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.gita.newauthcontactngrok.data.models.LocalData

@Database(entities = [LocalData::class], version = 1, exportSchema = false)
abstract class AppDatabase:RoomDatabase() {
    abstract fun contactDao():ContactDao

    companion object {
        private var instance:AppDatabase? = null
        fun init(context: Context) {
            instance = Room.databaseBuilder(context,AppDatabase::class.java,"contact.db")
                .build()
        }
        fun getInstance() = instance!!
    }
}