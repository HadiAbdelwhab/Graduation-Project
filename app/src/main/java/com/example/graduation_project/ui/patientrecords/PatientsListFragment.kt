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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduation_project.ui.MainActivity
import com.example.graduation_project.R
import com.example.graduation_project.adapters.PatientsAdapter
import com.example.graduation_project.databinding.FragmentPatientsListBinding
import com.example.graduation_project.models.patientsmodel.Patient
import com.example.graduation_project.util.Constants.Companion.TOKEN_KEY

class PatientsListFragment:Fragment(R.layout.fragment_patients_list) {

    private var _binding: FragmentPatientsListBinding? = null
    private val binding get() = _binding!!
    lateinit var patientsViewModel: PatientsViewModel
    private lateinit var patientsAdapter: PatientsAdapter
    private lateinit var sharedPreferences: SharedPreferences

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


        setUpRecyclerView()

        patientsViewModel=(activity as MainActivity).patientsViewModel

        val token = sharedPreferences.getString(TOKEN_KEY, null)

        if (token != null) {
            patientsViewModel.getPatientsList(token)

        }

        patientsViewModel.getPatientsList.observe(viewLifecycleOwner, Observer { patients ->
            patients?.let {

                patientsAdapter.updateData(it)
                //Log.d("PatientsListFragment",it.toString())

            }
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