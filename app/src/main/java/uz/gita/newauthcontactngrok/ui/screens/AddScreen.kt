package uz.gita.newauthcontactngrok.ui.screens

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.newauthcontactngrok.R
import uz.gita.newauthcontactngrok.databinding.ScreenAddBinding
import uz.gita.newauthcontactngrok.viewModel.AddViewModel
import uz.gita.newauthcontactngrok.viewModel.impl.AddViewModelImpl

class AddScreen : Fragment(R.layout.screen_add) {
    private val viewModel: AddViewModel by viewModels<AddViewModelImpl>()
    private val viewBinding: ScreenAddBinding by viewBinding(ScreenAddBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.addClick.observe(this) {
            findNavController().navigateUp()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.btnRegister.setOnClickListener {
            val name: String = viewBinding.etRegisterName.text.toString()
            val password = "+998${viewBinding.etRegisterPassword.rawText}"
            Toast.makeText(requireContext(), "$password", Toast.LENGTH_SHORT).show()
            Log.d("QQQQ", password)

            viewModel.postContact(name, password)
        }
        viewModel.message.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
        }
    }
}