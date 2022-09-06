package uz.gita.authcontactngrok.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.contactngrockonlineandofline.data.shP.MyShP
import uz.gita.newauthcontactngrok.R

class SplashFragment : Fragment(R.layout.screen_splash) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            delay(2000)
            if (MyShP.getInstance().getToken() == null) {
                findNavController().navigate(R.id.action_splashFragment_to_loginScreen)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_mainScreen)
            }
        }
    }
}