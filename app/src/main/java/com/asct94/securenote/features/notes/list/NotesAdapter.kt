package com.asct94.securenote.features.notes.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.asct94.securenote.databinding.ItemNoteBinding
import com.asct94.securenote.domain.models.Note


class NotesAdapter(
    private var notes: List<Note>
) : ListAdapter<Note, NotesAdapter.ViewHolder>(DiffUtilItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemNoteBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes.getOrNull(position) ?: return
        holder.binding.tvTitle.text = note.title
        holder.binding.tvMessage.text = note.message
    }

    override fun getItemCount() = notes.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<Note>) {
        this.notes = list
        notifyDataSetChanged()
    }

    class ViewHolder(
        val binding: ItemNoteBinding
    ) : RecyclerView.ViewHolder(binding.root)

    private object DiffUtilItemCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note) = oldItem == newItem
        override fun areContentsTheSame(oldItem: Note, newItem: Note) = oldItem == newItem
    }
}