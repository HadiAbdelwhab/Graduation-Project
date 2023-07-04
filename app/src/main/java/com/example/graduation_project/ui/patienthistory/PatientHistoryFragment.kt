package com.example.graduation_project.ui.patienthistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduation_project.R
import com.example.graduation_project.adapters.PatientsHistoryAdapter
import com.example.graduation_project.api.SessionManager
import com.example.graduation_project.databinding.FragmentPatientHistoryBinding
import com.example.graduation_project.models.patienthistorymodel.PatientHistory
import com.example.graduation_project.ui.MainActivity

class PatientHistoryFragment : Fragment(R.layout.fragment_patient_history) {

    private var _binding: FragmentPatientHistoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var patientHistoryViewModel: PatientHistoryViewModel
    private lateinit var patientHistoryAdapter: PatientsHistoryAdapter
    private lateinit var sessionManager: SessionManager

    private val arg: PatientHistoryFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPatientHistoryBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sessionManager = SessionManager((requireContext()))
        val id = arg.id

        setUpRecyclerView()

        patientHistoryViewModel = (activity as MainActivity).patientHistoryViewModel
        val token=sessionManager.fetchAuthToken()


        if (token != null) {
            patientHistoryViewModel.getPatientHistory(token,id)
        }

        patientHistoryViewModel.patientHistoryResult.observe(viewLifecycleOwner, Observer {

            patientHistoryAdapter.updateData(it as List<PatientHistory>)
        })

    }

    private fun setUpRecyclerView() {
        patientHistoryAdapter = PatientsHistoryAdapter(emptyList())
        binding.patientHistoryRecyclerView.apply {
            adapter = patientHistoryAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}