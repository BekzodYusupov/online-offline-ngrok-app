package uz.gita.newauthcontactngrok.viewModel.impl

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.gita.contactngrockonlineandofline.data.shP.MyShP
import uz.gita.newauthcontactngrok.data.models.LocalData
import uz.gita.newauthcontactngrok.data.models.UserData
import uz.gita.newauthcontactngrok.data.repository.ContactRepository
import uz.gita.newauthcontactngrok.data.repository.impl.AuthRepositoryImpl
import uz.gita.newauthcontactngrok.data.repository.impl.ContactRepositoryImpl
import uz.gita.newauthcontactngrok.viewModel.MainViewModel

class MainViewModelImpl : MainViewModel, ViewModel() {
    override val internetStateLIveData: MutableLiveData<String> = MutableLiveData()
    private val repository: ContactRepository = ContactRepositoryImpl()
    private val authRepo = AuthRepositoryImpl()
    private val shP = MyShP.getInstance()
    override val openAdd: MutableLiveData<Unit> = MutableLiveData()
    override val openLogin: MutableLiveData<Unit> = MutableLiveData()
    override val openEdit: MutableLiveData<LocalData> = MutableLiveData()
    override val contacts: LiveData<List<LocalData>> = repository.getContacts()

    override fun delete(localData: LocalData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(localData.id, localData.localId)
        }
    }
    init {
        if (repository.internetState()) {
            internetStateLIveData.value = "Online 🌍"
        } else {
            internetStateLIveData.value = "offline mode 🗿"
        }
    }

    override fun triggerAdd() {
        openAdd.value = Unit
    }

    override fun triggerEdit(localData: LocalData) {
        openEdit.value = localData
    }

    override fun triggerLogin() {
        openLogin.value = Unit
    }

    override fun triggerUnregister() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("HHHH", "${shP.getName()} and ${shP.getPassword()}")
            val name = shP.getName()!!
            val password = shP.getPassword()!!
            val userData = UserData(name, password)
            if (authRepo.unRegister(userData)) {
                Log.d("kkkk", "data cleared")
                repository.clearShP()
                repository.clearLocalDB()
                launch(Dispatchers.Main) {
                    openLogin.value = Unit
                }
            }
        }
    }

    override fun refresh() {
        if (repository.internetState()) {
            internetStateLIveData.value = "Online 🌍"
        } else {
            internetStateLIveData.value = "offline mode 🗿"
        }
        viewModelScope.launch(Dispatchers.IO) {
            repository.reLoadLocalData()
        }
    }
}