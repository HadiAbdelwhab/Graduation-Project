package com.example.graduation_project.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.graduation_project.R
import com.example.graduation_project.databinding.FragmentLogInBinding



class LoginFragment : Fragment(R.layout.fragment_log_in) {
    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registerTextView.setOnClickListener {
            // Navigate to the registration fragment
            val navController = Navigation.findNavController(requireView())
            navController.navigate(R.id.action_loginFragment_to_registrationFragment)
        }

        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.loginButton.setOnClickListener {
            val userName = binding.loginUsernameEditText.text.toString()
            val password = binding.loginPasswordEditText.text.toString()


        }
    }

}
//val navController = Navigation.findNavController(requireView())
//navController.navigate(R.id.action_homeFragment_to_favouriteFragment)
/*private fun handleLoginResult(result: Result<LoginResponse?>) {
    if (result.isSuccess) {
        // Login success
        val loginResponse = result.getOrNull() // Get the login response object
        // Perform further actions based on the login response

        // Example: Accessing the result items
        val resultItems = loginResponse?.results
        resultItems?.forEach { resultItem ->
            // Access individual result item properties (e.g., id, eye, result, etc.)
            val id = resultItem.id
            val eye = resultItem.eye
            val result = resultItem.result
            // Handle other properties as needed
        }

        // Navigate to the DashboardFragment and pass the login response as an argument
        val navController = Navigation.findNavController(requireView())
        val action = LoginFragmentDirections.actionLoginFragmentToDashboardFragment()
        navController.navigate(action)
    } else {
        // Login failure
        val errorMessage = result.exceptionOrNull()?.message // Get the error message
        // Handle the login failure (display error message, show error dialog, etc.)
    }
}*/
