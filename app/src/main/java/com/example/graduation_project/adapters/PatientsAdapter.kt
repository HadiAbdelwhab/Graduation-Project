package com.example.graduation_project.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.graduation_project.R
import com.example.graduation_project.models.patientsmodel.Patient

class PatientsAdapter(
) : RecyclerView.Adapter<PatientsAdapter.PatientsViewHolder>() {

    interface OnPatientClickListener {
        fun onPatientClick(patientId: Int)
    }

    private var onPatientClickListener: OnPatientClickListener? = null

    inner class PatientsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.text_view_name)
        private val genderTextView: TextView = itemView.findViewById(R.id.text_view_gender)
        private val ageTextView: TextView = itemView.findViewById<TextView?>(R.id.text_view_age)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val patientId = diifer.currentList[position].id
                    onPatientClickListener?.onPatientClick(patientId)
                }
            }
        }

        fun bind(patient: Patient) {
            nameTextView.text = "${patient.firstName} ${patient.lastName}"


            if (patient.gender == 1) {
                genderTextView.text = "Male"
            } else {
                ageTextView.text = "Female"
            }

            ageTextView.text = patient.birthDate.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_patient, parent, false)
        return PatientsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PatientsViewHolder, position: Int) {
        holder.bind(diifer.currentList[position])
    }

    override fun getItemCount(): Int {
        return diifer.currentList.size
    }

    fun getPatientAt(position: Int): Patient {
        return diifer.currentList[position]
    }

    fun removePatientAt(position: Int) {
        diifer.currentList.toMutableList().removeAt(position)
        notifyItemRemoved(position)
    }


    /*fun updateData(newPatientsList: List<Patient>) {
        diifer.currentList = newPatientsList
        notifyDataSetChanged()
    }*/

    fun setOnPatientClickListener(listener: OnPatientClickListener) {
        onPatientClickListener = listener
    }

    private val differCallback = object : DiffUtil.ItemCallback<Patient>() {
        override fun areItemsTheSame(oldItem: Patient, newItem: Patient): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Patient, newItem: Patient): Boolean {
            return oldItem == newItem
        }
    }
    val diifer = AsyncListDiffer(this, differCallback)

}
