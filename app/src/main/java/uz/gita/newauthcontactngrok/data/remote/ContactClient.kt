package uz.gita.newauthcontactngrok.data.remote

import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.newauthcontactngrok.app.App

object ContactClient {
    private val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    val client = OkHttpClient.Builder()
//        to get request and response result in emulator top bar
        .addInterceptor(ChuckerInterceptor.Builder(App.instance).build())
//        to get request and response result in logcat
        .addInterceptor(loggingInterceptor)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://9f62-94-141-76-59.eu.ngrok.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun getAuthApi(): AuthApi = retrofit.create(AuthApi::class.java)
    fun getContactApi(): ContactApi = retrofit.create(ContactApi::class.java)
}