package com.example.graduation_project.ui.patientrecords

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduation_project.ui.MainActivity
import com.example.graduation_project.R
import com.example.graduation_project.adapters.PatientsAdapter
import com.example.graduation_project.api.SessionManager
import com.example.graduation_project.databinding.FragmentPatientsListBinding
import com.example.graduation_project.models.patientsmodel.Patient
import com.example.graduation_project.util.Constants.Companion.PATIENT_ID
import com.example.graduation_project.util.Constants.Companion.SHA_PRF_KEY
import com.example.graduation_project.util.Constants.Companion.TOKEN_KEY

class PatientsListFragment : Fragment(R.layout.fragment_patients_list),
    PatientsAdapter.OnPatientClickListener {

    private var _binding: FragmentPatientsListBinding? = null
    private val binding get() = _binding!!
    lateinit var patientsViewModel: PatientsViewModel
    private lateinit var patientsAdapter: PatientsAdapter
    private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPatientsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sessionManager = SessionManager(requireContext())


        setUpRecyclerView()
        patientsAdapter.setOnPatientClickListener(this)

        patientsViewModel = (activity as MainActivity).patientsViewModel


        val token = sessionManager.fetchAuthToken()
        if (token != null) {
            patientsViewModel.getPatientsList(token)
        }
        patientsViewModel.getPatientsList.observe(viewLifecycleOwner, Observer { patients ->
            patients?.let {

                patientsAdapter.updateData(it)

            }
        })


    }

    private fun setUpRecyclerView() {
        patientsAdapter = PatientsAdapter(emptyList())
        binding.patientsListRecyclerView.apply {
            adapter = patientsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun navigateToPatientsHistoryFragment(patientId: Int) {
        val bundle=Bundle().putSerializable(PATIENT_ID,patientId)
        findNavController().navigate(
            R.id.action_dashboardFragment_to_patientHistoryFragment
        )
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onPatientClick(patientId: Int) {
        // Handle the patient click event here
        // You can access the patient ID and perform any necessary actions
        // Example: navigate to a patient details fragment with the ID
        navigateToPatientsHistoryFragment(patientId)
    }
}