package uz.gita.newauthcontactngrok.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import uz.gita.newauthcontactngrok.data.models.BaseData
import uz.gita.newauthcontactngrok.data.models.LoginData
import uz.gita.newauthcontactngrok.data.models.UserData

interface AuthApi {
    @POST("register")
    suspend fun register(
        @Body userData: UserData
    ): Response<BaseData<LoginData>>

    @POST("unregister")
    suspend fun unregister(
        @Body userData: UserData
    ): Response<BaseData<UserData?>>

    @POST("login")
    suspend fun login(
        @Body userData: UserData
    ): Response<BaseData<LoginData>>

    @POST("logout")
    suspend fun logOut(
        @Body userData: UserData
    ):Response<BaseData<UserData?>>
}