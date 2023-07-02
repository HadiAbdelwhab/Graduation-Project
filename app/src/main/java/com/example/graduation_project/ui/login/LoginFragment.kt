package com.example.graduation_project.ui.login

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.graduation_project.R
import com.example.graduation_project.databinding.FragmentLogInBinding
import com.example.graduation_project.models.loginmodel.LoginRequest
import com.example.graduation_project.ui.MainActivity


class LoginFragment : Fragment(R.layout.fragment_log_in) {
    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var sharedPreferences: SharedPreferences


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


        loginViewModel=(activity as MainActivity).loginViewModel
        sharedPreferences =
            requireActivity().getSharedPreferences("your_app_shared_prefs", Context.MODE_PRIVATE)

        binding.loginButton.setOnClickListener {
            val userName = binding.loginUsernameEditText.text.toString()
            val password = binding.loginPasswordEditText.text.toString()
            val loginRequest = LoginRequest(userName, password)
            loginViewModel.loginUser(loginRequest)
            Toast.makeText(context, "clicked", Toast.LENGTH_LONG).show()

        }

        // Observe the login result
        loginViewModel.loginResult.observe(viewLifecycleOwner) { loginResponse ->
            // Handle the login response

            if (loginResponse != null) {
                Toast.makeText(context, "not null", Toast.LENGTH_LONG).show()
                val token = loginResponse.access
                saveTokenToSharedPreferences(token)
                navigateToPatientsListFragment()
            } else {
                Toast.makeText(context, "JJJJJJ", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun saveTokenToSharedPreferences(token: String) {
        sharedPreferences.edit().putString("token", token).apply()
    }

    private fun navigateToPatientsListFragment() {
        val navController = Navigation.findNavController(requireView())
        navController.navigate(R.id.action_loginFragment_to_dashboardFragment)
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
