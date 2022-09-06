package uz.gita.newauthcontactngrok.ui.screens

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.authcontactngrok.ui.adapter.ContactAdapter
import uz.gita.newauthcontactngrok.R
import uz.gita.newauthcontactngrok.databinding.ScreenMainBinding
import uz.gita.newauthcontactngrok.utils.hasConnection
import uz.gita.newauthcontactngrok.viewModel.MainViewModel
import uz.gita.newauthcontactngrok.viewModel.impl.MainViewModelImpl

class MainScreen : Fragment(R.layout.screen_main) {
    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()
    private val viewBinding: ScreenMainBinding by viewBinding(ScreenMainBinding::bind)
    private val adapter: ContactAdapter by lazy { ContactAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.openAdd.observe(this) {
            findNavController().navigate(R.id.action_mainScreen_to_addScreen)
        }
        viewModel.openLogin.observe(this) {
            findNavController().navigate(R.id.action_mainScreen_to_loginScreen)
        }

        viewModel.openEdit.observe(this) {
            findNavController().navigate(MainScreenDirections.actionMainScreenToEditScreen(it))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.btnAddContact.setOnClickListener { viewModel.triggerAdd() }
        viewBinding.btnLogout.setOnClickListener { viewModel.triggerLogin() }
        viewBinding.btnUnRegister.setOnClickListener {
            viewBinding.progressbar.visibility = View.VISIBLE
            viewModel.triggerUnregister()
        }

        viewBinding.container.adapter = adapter
        viewModel.contacts.observe(viewLifecycleOwner) { adapter.submitList(it) }

        adapter.setDeleteListener {
            viewModel.delete(it)
        }
        adapter.setEditListener {
            viewModel.triggerEdit(it)
        }

        viewBinding.btnRefresh.setOnClickListener {
            if (!hasConnection()) Toast.makeText(
                requireContext(),
                "no internet 🗿",
                Toast.LENGTH_SHORT
            ).show()
            viewModel.refresh()

        }

        viewModel.internetStateLIveData.observe(viewLifecycleOwner) {
            viewBinding.internetState.text = it
        }
    }
}