package uz.gita.newauthcontactngrok.ui.screens

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.newauthcontactngrok.R
import uz.gita.newauthcontactngrok.databinding.ScreenRegisterBinding
import uz.gita.newauthcontactngrok.viewModel.RegisterViewModel
import uz.gita.newauthcontactngrok.viewModel.impl.RegisterViewModelImpl

class RegisterScreen:Fragment(R.layout.screen_register) {
    private val  viewModel: RegisterViewModel by viewModels<RegisterViewModelImpl>()
    private val viewBinding:ScreenRegisterBinding by viewBinding(ScreenRegisterBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.mainLivedata.observe(this){
            findNavController().navigate(R.id.action_registerScreen_to_mainScreen)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.btnRegister.setOnClickListener {
            val name:String = viewBinding.etRegisterName.text.toString()
            val password:String = viewBinding.etRegisterPassword.text.toString()
            viewModel.register(name, password)
        }
        viewModel.messageLIveData.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
        }
        viewBinding.btnLogin.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}