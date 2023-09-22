package com.asct94.securenote.features.notes.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.asct94.securenote.Note
import com.asct94.securenote.databinding.FragmentNoteListBinding

class NoteListFragment : Fragment() {

    private lateinit var binding: FragmentNoteListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvNotes.adapter = NotesAdapter(
            listOf(
                Note(
                    "Title 1",
                    "Vestibulum sit amet sapien a justo placerat volutpat at ac dolor. Duis dignissim ut leo ut volutpat. Proin nec ultricies ipsum, ac cursus metus. Aliquam vitae ornare elit. In hendrerit dui in facilisis volutpat."
                ),
                Note(
                    "Title 2",
                    "Sed iaculis, dui sit amet tempor placerat, urna turpis pulvinar ex, vel varius ipsum leo vel velit. Maecenas sem turpis, pellentesque a commodo ut, mattis sagittis mauris. "
                ),
                Note(
                    "Title 3",
                    "Sed pharetra erat vitae nisi laoreet suscipit. Donec eu nibh a orci sagittis vehicula. Quisque ultricies neque eu sem iaculis feugiat. Proin dictum aliquam dolor. In nec metus aliquet, mattis elit sed, semper mi."
                )
            )
        )
    }
}