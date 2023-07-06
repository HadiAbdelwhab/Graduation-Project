package com.example.graduation_project.ui.createnewpatient

import android.app.DatePickerDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.graduation_project.R
import com.example.graduation_project.api.SessionManager
import com.example.graduation_project.databinding.DialogCreateNewPatientBinding
import com.example.graduation_project.models.createnewpatientmodel.CreateNewPatientRequest
import com.example.graduation_project.ui.MainActivity
import java.text.SimpleDateFormat
import java.util.*


class CreateNewPatientDialog : DialogFragment(R.layout.dialog_create_new_patient) {

    private var _binding: DialogCreateNewPatientBinding? = null
    private val binding get() = _binding!!

    private lateinit var createNewPatientViewModel: CreateNewPatientViewModel
    private lateinit var datePickerDialog: DatePickerDialog
    private lateinit var sessionManager: SessionManager
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogCreateNewPatientBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.85).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sessionManager = SessionManager(requireContext())

        createNewPatientViewModel = (activity as MainActivity).createNewPatientViewModel








        binding.pickDateButton.setOnClickListener {
            showDatePicker()
        }
        val token = sessionManager.fetchAuthToken()

        binding.saveButton.setOnClickListener {


            val token = sessionManager.fetchAuthToken()
            val patientData = gatherPatientDataFromInputFields()
            if (token != null) {
                createNewPatientViewModel.createNewPatient(
                    token = token,
                    createNewPatientRequest = patientData
                )
            }
            dismiss()
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        datePickerDialog =
            DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
                // Handle the selected date
                val formattedDate = formatDate(selectedYear, selectedMonth, selectedDay)
                binding.dateTextView.text = formattedDate
            }, year, month, day)

        datePickerDialog.show()
    }

    private fun formatDate(year: Int, month: Int, day: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

    private fun gatherPatientDataFromInputFields(): CreateNewPatientRequest {
        val firstName = binding.firstNameEditText.text.toString()
        val lastName = binding.lastNameEditText.text.toString()
        val dateOfBirth = binding.dateTextView.text.toString()
        val gender: Int? = when (binding.genderRadioGroup.checkedRadioButtonId) {
            R.id.male_radio_button -> 1
            R.id.female_radio_button -> 0
            else -> null
        }
        // Gather other relevant fields as needed
        return CreateNewPatientRequest(firstName, lastName, gender!!, dateOfBirth)
    }


}
