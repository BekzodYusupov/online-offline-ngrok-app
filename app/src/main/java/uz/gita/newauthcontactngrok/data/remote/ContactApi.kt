package uz.gita.newauthcontactngrok.data.remote

import retrofit2.Response
import retrofit2.http.*
import uz.gita.newauthcontactngrok.data.models.BaseData
import uz.gita.newauthcontactngrok.data.models.RequestData
import uz.gita.newauthcontactngrok.data.models.ResponseData

interface ContactApi {
    @GET("contact")
    suspend fun getContacts(
        @Header("token") token: String
    ): Response<BaseData<List<ResponseData>>>

    @POST("contact")
    suspend fun postContact(
        @Header("token") token: String,
        @Body requestData: RequestData
    ): Response<BaseData<ResponseData>>

    @PUT("contact")
    suspend fun putContact(
        @Header("token") token: String,
        @Query("id") id: Int,
        @Body requestData: RequestData
    ): Response<BaseData<ResponseData>>

    @DELETE("contact")
    suspend fun delete(
        @Query("id") id: Int,
        @Header("token") token: String
    ): Response<BaseData<ResponseData>>

    @GET("contact")
    suspend fun getContact(
        @Query("id") id: Int,
        @Header("token") token: String
    ):Response<BaseData<ResponseData>>
}