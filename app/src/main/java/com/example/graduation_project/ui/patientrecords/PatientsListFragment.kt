package com.example.graduation_project.ui.patientrecords

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduation_project.R
import com.example.graduation_project.adapters.PatientsAdapter
import com.example.graduation_project.databinding.FragmentPatientsListBinding
import com.example.graduation_project.models.patientsmodel.Patient
import com.example.graduation_project.ui.login.LoginViewModel

class PatientsListFragment:Fragment(R.layout.fragment_patients_list) {

    private var _binding: FragmentPatientsListBinding? = null
    private val binding get() = _binding!!
    private lateinit var patientsViewModel: PatientsViewModel
    private lateinit var patientsAdapter: PatientsAdapter
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

        patientsViewModel= ViewModelProvider(this)[PatientsViewModel::class.java]

        setUpRecyclerView()

        patientsViewModel.getPatientsList.observe(viewLifecycleOwner, Observer {
            patientsAdapter.updateData(it as List<Patient>)
        })


    }

    private fun setUpRecyclerView() {
        patientsAdapter= PatientsAdapter(emptyList())
        binding.patientsListRecyclerView.apply {
            adapter = patientsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}