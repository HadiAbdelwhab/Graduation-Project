package com.example.graduation_project.ui.registration

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.graduation_project.R
import com.example.graduation_project.databinding.FragmentRegistrationBinding
import com.example.graduation_project.models.registermodel.RegistrationRequest
import com.example.graduation_project.ui.MainActivity

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    private lateinit var registrationViewModel: RegistrationViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registrationViewModel = (activity as MainActivity).registerViewModel

        binding.registerButton.setOnClickListener {
            val firstName = binding.firstNameEditText.text.toString()
            val lastName = binding.lastNameEditText.text.toString()
            val username = binding.registerUsernameEditText.text.toString()
            val email = binding.registerEmailEditText.text.toString()
            val password = binding.registerPasswordEditText.text.toString()
            val confirmPassword = binding.confirmPasswordEditText.text.toString()
            val registrationRequest = RegistrationRequest(

                username,
                email,
                firstName,
                lastName,
                password,
                confirmPassword

            )
            Log.d("RegistrationFragment",registrationRequest.toString())

            registrationViewModel.registerUser(registrationRequest)


        }

        registrationViewModel.registerResult.observe(viewLifecycleOwner) { registrationResponse ->
            // Handle the login response

            if (registrationResponse != null) {


                Toast.makeText(context, "Registration Succeeded ", Toast.LENGTH_LONG).show()
                navigateToLoginFragment()


            } else {
                Toast.makeText(context, "Registration Failed", Toast.LENGTH_LONG).show()
            }
        }


    }

    private fun navigateToLoginFragment() {
        val navController = Navigation.findNavController(requireView())
        navController.navigate(R.id.action_registrationFragment_to_loginFragment)
    }


}