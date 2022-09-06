package uz.gita.newauthcontactngrok.viewModel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.gita.newauthcontactngrok.data.models.RequestData
import uz.gita.newauthcontactngrok.data.repository.impl.ContactRepositoryImpl
import uz.gita.newauthcontactngrok.viewModel.EditViewModel

class EditViewModelImpl : EditViewModel, ViewModel() {
    private val repository = ContactRepositoryImpl()
    override val mainLiveData = MutableLiveData<Unit>()

    override fun edit(name: String, phone: String, id: Int, localId: Int) {
        if (check(name, phone)) {
            viewModelScope.launch(Dispatchers.IO) {
                val requestData = RequestData(name, phone)
                if (repository.putContact(id, requestData, localId)) {
                    launch(Dispatchers.Main) {
                        mainLiveData.value = Unit
                    }
                }
            }
        }
    }

    private fun check(name: String, number: String): Boolean {
        return (name.length >= 3 && number.startsWith("+998") && number.length == 13)
    }
}