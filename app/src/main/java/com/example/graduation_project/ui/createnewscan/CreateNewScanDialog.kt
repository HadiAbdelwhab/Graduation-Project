package com.example.graduation_project.ui.createnewscan

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import com.example.graduation_project.R
import com.example.graduation_project.api.SessionManager
import com.example.graduation_project.databinding.DialogCreateNewScanBinding
import com.example.graduation_project.models.createnewscanmodel.CreateNewScanRequest
import com.example.graduation_project.ui.MainActivity
import com.example.graduation_project.util.Constants.Companion.PATIENT_ID
import java.io.File

class CreateNewScanDialog : DialogFragment(R.layout.dialog_create_new_scan) {

    private var _binding: DialogCreateNewScanBinding? = null
    private val binding get() = _binding!!

    private lateinit var createNewScanViewModel: CreateNewScanViewModel
    private lateinit var sessionManager: SessionManager


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogCreateNewScanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        //val height = (resources.displayMetrics.heightPixels * 0.85).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createNewScanViewModel = (activity as MainActivity).createNewScanViewModel
        sessionManager = SessionManager(requireContext())



        var eyeImage: Uri? = null

        binding.addImageButton.setOnClickListener {
            val pickImg = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)

            changeImage.launch(pickImg)
        }

        binding.saveNewScan.setOnClickListener {
            val token = sessionManager.fetchAuthToken()
            val note = binding.notesEditText.text.toString()
            val id = requireArguments().getInt(PATIENT_ID)
            val eye: Int? = when (binding.eyeRadioGroup.checkedRadioButtonId) {
                R.id.right_eye_radio_button -> 1
                R.id.left_eye_radio_button -> 2
                else -> null
            }


            val imageFile: File? = eyeImage?.let { uri ->
                // Convert Uri to File
                val filePath = getPathFromUri(uri)
                if (filePath != null) {
                    File(filePath)
                } else {
                    null
                }
            }
            val request = CreateNewScanRequest(
                eye ?: return@setOnClickListener,
                note,
                id
            )
            if (token != null && imageFile != null) {
                createNewScanViewModel.createNewScan(token, imageFile, request)
            } else {
                // Handle the case where token or imageFile is null
            }
            dismiss()
        }

        val changeImage =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val selectedImage: Uri? = result.data?.data
                    eyeImage = selectedImage
                    // Update the ImageView or perform any other necessary actions with the selected image
                }
            }
    }

    private fun getPathFromUri(uri: Uri): String? {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor? = context?.contentResolver?.query(uri, projection, null, null, null)
        return cursor?.use {
            val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            it.moveToFirst()
            it.getString(columnIndex)
        }
    }


    private val changeImage =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val data = it.data
                val imgUri = data?.data
                binding.eyeImageView.setImageURI(imgUri)
            }
        }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}