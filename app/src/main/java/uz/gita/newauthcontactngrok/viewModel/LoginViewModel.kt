package uz.gita.newauthcontactngrok.viewModel

import androidx.lifecycle.LiveData

interface LoginViewModel {
    val messageLIveData:LiveData<String>
    val registerLivedata: LiveData<Unit>
    val mainLivedata: LiveData<Unit>

    fun login(name: String, password: String)
    fun triggerRegister()
}