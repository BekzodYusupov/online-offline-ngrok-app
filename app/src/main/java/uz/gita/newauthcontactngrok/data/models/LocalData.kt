package uz.gita.newauthcontactngrok.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "contact")
data class LocalData(
    val id: Int,
    val name: String,
    val phone: String,
    @PrimaryKey(autoGenerate = true)
    val localId: Int = 0,
    val state: Int = 0
):Serializable