package uz.gita.newauthcontactngrok.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.newauthcontactngrok.R
import uz.gita.newauthcontactngrok.databinding.ScreenEditBinding
import uz.gita.newauthcontactngrok.viewModel.EditViewModel
import uz.gita.newauthcontactngrok.viewModel.impl.EditViewModelImpl

class EditScreen : Fragment(R.layout.screen_edit) {
    private val args: EditScreenArgs by navArgs()
    private val viewBinding: ScreenEditBinding by viewBinding(ScreenEditBinding::bind)
    private val viewModel: EditViewModel by viewModels<EditViewModelImpl>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.mainLiveData.observe(this) {
            findNavController().navigateUp()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.etName.setText(args.localData.name)
        viewBinding.etNum.setText(args.localData.phone)


        viewBinding.btnRegister.setOnClickListener {
            val name = viewBinding.etName.text.toString()
            val number = viewBinding.etNum.text.toString()
            viewModel.edit(name, number, args.localData.id, args.localData.localId)
        }
    }
}