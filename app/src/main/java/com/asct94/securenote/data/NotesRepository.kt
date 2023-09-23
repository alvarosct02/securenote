package com.asct94.securenote.data

import com.asct94.securenote.domain.models.Note
import javax.inject.Inject
import kotlinx.coroutines.delay


class NotesRepository @Inject constructor() {

    suspend fun getNotes(): List<Note> {
        delay(1000)
        return listOf(
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
    }
}