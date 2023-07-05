package com.example.graduation_project.ui.login

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.graduation_project.R
import com.example.graduation_project.api.SessionManager
import com.example.graduation_project.databinding.FragmentLogInBinding
import com.example.graduation_project.models.loginmodel.LoginRequest
import com.example.graduation_project.ui.MainActivity
import com.example.graduation_project.util.Constants.Companion.SHA_PRF_KEY



class LoginFragment : Fragment(R.layout.fragment_log_in) {
    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sessionManager: SessionManager




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        sessionManager = SessionManager(requireContext())

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerFragmentTextView.setOnClickListener {
            val navController = Navigation.findNavController(requireView())
            navController.navigate(R.id.action_loginFragment_to_registrationFragment)

        }

        loginViewModel=(activity as MainActivity).loginViewModel
        sharedPreferences =
            requireActivity().getSharedPreferences(SHA_PRF_KEY, Context.MODE_PRIVATE)

        binding.loginButton.setOnClickListener {
            val userName = binding.loginUsernameEditText.text.toString()
            val password = binding.loginPasswordEditText.text.toString()
            val loginRequest = LoginRequest(userName, password)
            loginViewModel.loginUser(loginRequest)
            Toast.makeText(context, "clicked", Toast.LENGTH_LONG).show()

        }

        loginViewModel.loginResult.observe(viewLifecycleOwner) { loginResponse ->

            if (loginResponse != null) {

                val token = loginResponse.access
                sessionManager.saveAuthToken(token)

                navigateToPatientsListFragment()

            } else {
                Toast.makeText(context, "Server error", Toast.LENGTH_LONG).show()
            }
        }
    }



    private fun navigateToPatientsListFragment() {
        val navController = Navigation.findNavController(requireView())
        navController.navigate(R.id.action_loginFragment_to_dashboardFragment)
    }
}

