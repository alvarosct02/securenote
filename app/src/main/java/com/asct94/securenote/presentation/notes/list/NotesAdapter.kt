package com.asct94.securenote.presentation.notes.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.asct94.securenote.databinding.ItemNoteBinding
import com.asct94.securenote.domain.models.Note


class NotesAdapter(
    private val onItemClick: (Note) -> Unit
) : ListAdapter<Note, NotesAdapter.ViewHolder>(DiffUtilItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemNoteBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = getItem(position) ?: return
        holder.binding.note = note
        holder.binding.root.setOnClickListener {
            onItemClick(note)
        }
    }

    class ViewHolder(
        val binding: ItemNoteBinding
    ) : RecyclerView.ViewHolder(binding.root)

    private object DiffUtilItemCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note) = oldItem == newItem
        override fun areContentsTheSame(oldItem: Note, newItem: Note) = oldItem == newItem
    }
}