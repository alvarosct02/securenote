package com.asct94.securenote.features.notes.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asct94.securenote.Note
import com.asct94.securenote.databinding.ItemNoteBinding


class NotesAdapter(
    private val notes: List<Note>
) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemNoteBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes.getOrNull(position) ?: return
        holder.binding.tvTitle.text = note.title
        holder.binding.tvMessage.text = note.message
    }

    class ViewHolder(
        val binding: ItemNoteBinding
    ) : RecyclerView.ViewHolder(binding.root)
}