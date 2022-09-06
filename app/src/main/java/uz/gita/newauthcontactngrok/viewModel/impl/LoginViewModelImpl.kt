package uz.gita.newauthcontactngrok.viewModel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.gita.contactngrockonlineandofline.data.shP.MyShP
import uz.gita.newauthcontactngrok.data.models.UserData
import uz.gita.newauthcontactngrok.data.repository.AuthRepository
import uz.gita.newauthcontactngrok.data.repository.impl.AuthRepositoryImpl
import uz.gita.newauthcontactngrok.viewModel.LoginViewModel

class LoginViewModelImpl : LoginViewModel, ViewModel() {
    override val messageLIveData=MutableLiveData<String>()
    private val repo: AuthRepository = AuthRepositoryImpl()
    override val registerLivedata = MutableLiveData<Unit>()
    override val mainLivedata = MutableLiveData<Unit>()

    override fun login(name: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (repo.login(UserData(name, password))) {
                launch(Dispatchers.Main) {
                    messageLIveData.value = "logged in successfully"
                    mainLivedata.value = Unit
                }
            } else {
                messageLIveData.postValue("Error pls check username or password")
            }
        }
    }

    override fun triggerRegister() {
        registerLivedata.value = Unit
    }
}