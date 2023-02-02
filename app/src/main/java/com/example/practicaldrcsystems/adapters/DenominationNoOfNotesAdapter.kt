package com.example.practicaldrcsystems.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaldrcsystems.databinding.ItemNotesAmountDenominationBinding
import com.example.practicaldrcsystems.models.NotesAmountModel

class DenominationNoOfNotesAdapter(
    private val notesList: List<NotesAmountModel>
) :
    RecyclerView.Adapter<DenominationNoOfNotesAdapter.DenominationNoOfNotesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DenominationNoOfNotesViewHolder {

        val binding = ItemNotesAmountDenominationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DenominationNoOfNotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DenominationNoOfNotesViewHolder, position: Int) {
        val notesAmountModel: NotesAmountModel = notesList[position]
        holder.apply {
            binding.apply {
                if (notesAmountModel.denomination != 0){
                    cnstMainParent.visibility = View.VISIBLE
                    mcrdDenomination.visibility = View.VISIBLE
                    mtxtDenomination.text = notesAmountModel.denomination.toString()
                }
                if (notesAmountModel.noOfNotes != 0){
                    mcrdNoOfNotes.visibility = View.VISIBLE
                    mtxtNoOfNotes.text = notesAmountModel.noOfNotes.toString()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return notesList.size
        Log.d("CheckCameInAdapter", "getItemCount: ${notesList.size}")
    }

    inner class DenominationNoOfNotesViewHolder(val binding: ItemNotesAmountDenominationBinding) :
        RecyclerView.ViewHolder(binding.root) {}
}