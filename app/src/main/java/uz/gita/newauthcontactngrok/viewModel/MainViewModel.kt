package uz.gita.newauthcontactngrok.viewModel

import androidx.lifecycle.LiveData
import uz.gita.newauthcontactngrok.data.models.LocalData
import uz.gita.newauthcontactngrok.data.models.UserData

interface MainViewModel {
    val internetStateLIveData:LiveData<String>
    val openAdd:LiveData<Unit>
    val openLogin:LiveData<Unit>
    val openEdit:LiveData<LocalData>
    val contacts:LiveData<List<LocalData>>

    fun delete(localData: LocalData)
    fun triggerAdd()
    fun triggerEdit(localData: LocalData)
    fun triggerLogin()
    fun triggerUnregister()
    fun refresh()
}