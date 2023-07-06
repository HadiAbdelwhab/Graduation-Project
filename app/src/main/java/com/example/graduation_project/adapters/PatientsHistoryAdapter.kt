package com.example.graduation_project.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.graduation_project.R
import com.example.graduation_project.models.patienthistorymodel.PatientHistory
import com.example.graduation_project.models.patientsmodel.Patient

class PatientsHistoryAdapter(

) : RecyclerView.Adapter<PatientsHistoryAdapter.PatientHistoryViewHolder>() {
    inner class PatientHistoryViewHolder(item: View):RecyclerView.ViewHolder(item) {

        private val resultTextView:TextView=item.findViewById(R.id.text_view_result)
        private val eyeTextView:TextView=item.findViewById(R.id.text_view_eye)
        private val ratioTextView:TextView=item.findViewById(R.id.text_view_ratio)
        private val maskImageView:ImageView=item.findViewById(R.id.mask_image_view)

        fun bind(patientHistory: PatientHistory){

            resultTextView.text =patientHistory.result.toString()
            eyeTextView.text=patientHistory.eye.toString()
            ratioTextView.text=patientHistory.ratio.toString()
            Glide.with(itemView)
                .load(patientHistory.mask)
                .into(maskImageView)

        }


    }
    private val differCallback = object : DiffUtil.ItemCallback<PatientHistory>() {
        override fun areItemsTheSame(oldItem: PatientHistory, newItem: PatientHistory): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PatientHistory, newItem: PatientHistory): Boolean {
            return oldItem == newItem
        }
    }
    val diifer = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PatientHistoryViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_patient, parent, false)
        return PatientHistoryViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return diifer.currentList.size
    }

    override fun onBindViewHolder(holder: PatientHistoryViewHolder, position: Int) {
        holder.bind(diifer.currentList[position])
    }




}