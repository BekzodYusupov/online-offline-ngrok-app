package uz.gita.contactngrockonlineandofline.data.shP

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class MyShP private constructor() {

    companion object {
        private lateinit var sharedPref: SharedPreferences
        private lateinit var editor: SharedPreferences.Editor
        private var shp: MyShP? = null

        fun init(context: Context) {
            shp = MyShP()
            sharedPref = context.getSharedPreferences("auth", MODE_PRIVATE)
            editor = sharedPref.edit()
        }

        fun getInstance() = shp!!
    }

    fun setToken(token: String) {
        editor.putString("TOKEN", token)
        editor.apply()
    }

    fun getToken(): String? {
        return sharedPref.getString("TOKEN", null)
    }

    fun setName(name: String) {
        editor.putString("NAME", name)
        editor.apply()
    }

    fun getName(): String? {
        return sharedPref.getString("NAME", null)
    }

    fun setPassword(password: String) {
        editor.putString("PASSWORD", password)
        editor.apply()
    }

    fun getPassword(): String? {
        return sharedPref.getString("PASSWORD", null)
    }

    fun clear() {
        editor.clear()
    }
}