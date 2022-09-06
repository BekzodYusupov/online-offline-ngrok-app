package uz.gita.newauthcontactngrok.data.models

data class BaseData<T>(
    val message: String,
    val data: T
)
