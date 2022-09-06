package uz.gita.newauthcontactngrok.viewModel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.gita.newauthcontactngrok.data.models.RequestData
import uz.gita.newauthcontactngrok.data.repository.ContactRepository
import uz.gita.newauthcontactngrok.data.repository.impl.ContactRepositoryImpl
import uz.gita.newauthcontactngrok.viewModel.AddViewModel

class AddViewModelImpl : AddViewModel, ViewModel() {
    override val message = MutableLiveData<String>()
    private val repository: ContactRepository = ContactRepositoryImpl()
    override val addClick: MutableLiveData<Unit> = MutableLiveData()

    override fun postContact(name: String, number: String) {
        if (check(name, number)) {
            val requestData = RequestData(name, number)
            viewModelScope.launch(Dispatchers.IO) {
                if (repository.postContact(requestData)) {
                    addClick.postValue(Unit)
                    message.postValue("adding pls wait")
                }
            }
        } else {
            message.value = "Pls Check your inputs"
        }
    }

    private fun check(name: String, number: String): Boolean {
        return (name.length >= 3 && number.startsWith("+998") && number.length == 13)
    }
}