package com.asct94.securenote.data.di

import com.asct94.securenote.data.DefaultNotesRepository
import com.asct94.securenote.domain.repositories.NotesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindNotesRepository(defaultNotesRepository: DefaultNotesRepository): NotesRepository
}
