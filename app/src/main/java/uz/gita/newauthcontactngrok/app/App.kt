package uz.gita.newauthcontactngrok.app

import android.app.Application
import kotlinx.coroutines.selects.SelectInstance
import uz.gita.contactngrockonlineandofline.data.shP.MyShP
import uz.gita.newauthcontactngrok.data.local.AppDatabase

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        AppDatabase.init(this)
        MyShP.init(this)
    }
}