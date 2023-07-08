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
import com.example.graduation_project.models.patientsmodel.Patient
import com.example.graduation_project.ui.createnewpatient.CreateNewPatientViewModel
import com.example.graduation_project.util.Constants.Companion.PATIENT_ID
import java.util.*

class PatientsListFragment : Fragment(R.layout.fragment_patients_list),
    PatientsAdapter.OnPatientClickListener {

    private var _binding: FragmentPatientsListBinding? = null
    private val binding get() = _binding!!
    lateinit var patientsViewModel: PatientsViewModel
    private lateinit var patientsAdapter: PatientsAdapter
    private lateinit var sessionManager: SessionManager
    private lateinit var createNewPatientViewModel: CreateNewPatientViewModel

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

        createNewPatientViewModel = (activity as MainActivity).createNewPatientViewModel

        createNewPatientViewModel.createNewPatientResult.observe(
            viewLifecycleOwner,
            Observer { response ->
                val patient = Patient(
                    response.date,
                    currentDate(),
                    response.firstName,
                    response.gender,
                    response.id,
                    response.lastName
                )
                patientsAdapter.addPatient(patient)
            })
        setUpRecyclerView()

        patientsAdapter.setOnPatientClickListener(this)

        val token = sessionManager.fetchAuthToken()

        if (token != null) {
            patientsViewModel.getPatientsList(token)
            setupSwipe(token)
        }




        patientsViewModel.getPatientsList.observe(viewLifecycleOwner, Observer { patients ->
            patients?.let { list ->
                patientsAdapter.updateData(list)
                if (list.isEmpty()) {
                    hideRecyclerView()
                } else {
                    showRecyclerView()
                }

            }
        })

        binding.logOutImageView.setOnClickListener {
            findNavController().navigate(
                R.id.action_dashboardFragment_to_loginFragment
            )
            Toast.makeText(context, "Logged out", Toast.LENGTH_SHORT).show()
        }

        binding.addNewPatientButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_dashboardFragment_to_createNewPatientDialog
            )
        }


    }

    private fun setUpRecyclerView() {
        patientsAdapter = PatientsAdapter(emptyList())
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

    private fun setupSwipe(token: String) {
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
                val swipedPatient = patientsAdapter.getPatientAt(swipedPatientPosition)
                patientsViewModel.deletePatient(swipedPatient.id, token)
                patientsAdapter.removePatientAt(swipedPatientPosition)
                patientsAdapter.notifyItemRemoved(swipedPatientPosition)


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

    private fun showRecyclerView() {
        binding.patientsListRecyclerView.visibility = View.VISIBLE
        binding.noPatientsTextView.visibility = View.GONE

    }

    private fun hideRecyclerView() {
        binding.patientsListRecyclerView.visibility = View.GONE
        binding.noPatientsTextView.visibility = View.VISIBLE
    }

    private fun currentDate(): String {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        return "${year.toString()}-${month.toString()}-${day.toString()}"
    }


}



