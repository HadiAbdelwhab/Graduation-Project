package com.example.graduation_project.ui.patientrecords

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.graduation_project.ui.MainActivity
import com.example.graduation_project.R
import com.example.graduation_project.adapters.PatientsAdapter
import com.example.graduation_project.api.SessionManager
import com.example.graduation_project.databinding.FragmentPatientsListBinding
import com.example.graduation_project.util.Constants.Companion.PATIENT_ID
import com.google.android.material.snackbar.Snackbar

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

        patientsViewModel = (activity as MainActivity).patientsViewModel

        setUpRecyclerView()

        patientsAdapter.setOnPatientClickListener(this)

        val token = sessionManager.fetchAuthToken()

        if (token != null) {
            patientsViewModel.getPatientsList(token)
            setupSwipe(token)
        }





        patientsViewModel.getPatientsList.observe(viewLifecycleOwner, Observer { patients ->
            patients?.let {

                patientsAdapter.diifer.submitList(it)

            }
        })


    }

    private fun setUpRecyclerView() {
        patientsAdapter = PatientsAdapter()
        binding.patientsListRecyclerView.apply {
            adapter = patientsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun navigateToPatientsHistoryFragment(patientId: Int?) {
        val bundle = Bundle().apply {
            if (patientId != null) {
                putInt(PATIENT_ID, patientId)
                Log.d("PatientsListFragment", patientId.toString())
                Toast.makeText(context, "$patientId", Toast.LENGTH_LONG).show()

            }
        }
        findNavController().navigate(
            R.id.action_dashboardFragment_to_patientHistoryFragment,
            bundle
        )
    }

    private fun setupSwipe(toke: String) {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val swipedPatientPosition = viewHolder.adapterPosition
                val swipedPatient = patientsAdapter.diifer.currentList[swipedPatientPosition]
                patientsViewModel.deletePatient(swipedPatient.id, toke)
                Toast.makeText(context, "Patient deleted!", Toast.LENGTH_SHORT).show()

            }

        }


        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.patientsListRecyclerView)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null


    }

    override fun onPatientClick(patientId: Int) {

        navigateToPatientsHistoryFragment(patientId)
        Log.d("PatientsListFragment", patientId.toString())
    }
}