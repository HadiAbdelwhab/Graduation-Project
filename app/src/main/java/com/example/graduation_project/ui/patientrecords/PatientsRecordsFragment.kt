package com.example.graduation_project.ui.patientrecords

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.graduation_project.R
import com.example.graduation_project.databinding.FragmentPatientsRecordsBinding

class PatientsRecordsFragment:Fragment(R.layout.fragment_patients_records) {

    private var _binding: FragmentPatientsRecordsBinding? = null
    private val binding get() = _binding!!
    //private val args: DashboardFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPatientsRecordsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}