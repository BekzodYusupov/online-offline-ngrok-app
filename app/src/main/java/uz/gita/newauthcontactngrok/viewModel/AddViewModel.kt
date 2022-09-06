package uz.gita.newauthcontactngrok.viewModel

import androidx.lifecycle.LiveData

interface AddViewModel {
    val addClick: LiveData<Unit>
    val message:LiveData<String>

    fun postContact(name: String, number: String)
}