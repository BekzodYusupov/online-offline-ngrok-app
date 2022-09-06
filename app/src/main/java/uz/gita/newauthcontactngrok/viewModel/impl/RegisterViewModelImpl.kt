package uz.gita.newauthcontactngrok.viewModel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.gita.contactngrockonlineandofline.data.shP.MyShP
import uz.gita.newauthcontactngrok.data.models.UserData
import uz.gita.newauthcontactngrok.data.repository.AuthRepository
import uz.gita.newauthcontactngrok.data.repository.impl.AuthRepositoryImpl
import uz.gita.newauthcontactngrok.viewModel.RegisterViewModel

class RegisterViewModelImpl : RegisterViewModel, ViewModel() {
    override val messageLIveData = MutableLiveData<String>()
    private val repo: AuthRepository = AuthRepositoryImpl()
    private val shP: MyShP = MyShP.getInstance()
    override val mainLivedata = MutableLiveData<Unit>()
    override val loginLiveData = MutableLiveData<Unit>()

    override fun register(name: String, password: String) {
        if (check(name, password)) {
            viewModelScope.launch(Dispatchers.IO) {
                messageLIveData.postValue("registered in successfully")
                val userData = UserData(name, password)
                if (repo.register(userData)) {
                    shP.setName(userData.name)
                    shP.setPassword(userData.password)
                    launch(Dispatchers.Main) { mainLivedata.value = Unit }
                }
            }
        } else {
            messageLIveData.postValue("Error pls check username or password")
        }
    }

    override fun triggerLogin() {
        loginLiveData.value = Unit
    }

    private fun check(name: String, password: String): Boolean =
        (name.length > 2 && password.length > 2)

}