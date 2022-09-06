package uz.gita.newauthcontactngrok.ui.screens

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.newauthcontactngrok.R
import uz.gita.newauthcontactngrok.databinding.ScreenLoginBinding
import uz.gita.newauthcontactngrok.viewModel.LoginViewModel
import uz.gita.newauthcontactngrok.viewModel.impl.LoginViewModelImpl

class LoginScreen:Fragment(R.layout.screen_login) {
    private val viewModel: LoginViewModel by viewModels<LoginViewModelImpl>()
    private val viewBinding: ScreenLoginBinding by viewBinding(ScreenLoginBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.mainLivedata.observe(this){
            findNavController().navigate(R.id.action_loginScreen_to_mainScreen)
        }
        viewModel.registerLivedata.observe(this){
            findNavController().navigate(R.id.action_loginScreen_to_registerScreen)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.btnRegister.setOnClickListener {
            viewModel.triggerRegister()
        }
        viewBinding.btnLogin.setOnClickListener {
            val name:String = viewBinding.etRegisterName.text.toString()
            val password:String = viewBinding.etRegisterPassword.text.toString()
            viewModel.login(name, password)
        }

        viewModel.messageLIveData.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
        }
    }
}