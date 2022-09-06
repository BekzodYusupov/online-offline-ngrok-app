package uz.gita.newauthcontactngrok.viewModel

import androidx.lifecycle.LiveData

interface EditViewModel {
    val mainLiveData: LiveData<Unit>

    fun edit(name:String, phone:String,id:Int, localId:Int)
}