package uz.gita.newauthcontactngrok.data.repository

import uz.gita.newauthcontactngrok.data.models.UserData

interface AuthRepository {
    suspend fun register(userData: UserData):Boolean
    suspend fun unRegister(userData: UserData):Boolean
    suspend fun login(userData: UserData):Boolean
    suspend fun logout(userData: UserData)
}