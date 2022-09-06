package uz.gita.newauthcontactngrok.viewModel

import androidx.lifecycle.LiveData

interface RegisterViewModel {
    val messageLIveData:LiveData<String>
    val mainLivedata: LiveData<Unit>
    val loginLiveData:LiveData<Unit>
    fun register(name:String, password:String)

    fun triggerLogin()

}