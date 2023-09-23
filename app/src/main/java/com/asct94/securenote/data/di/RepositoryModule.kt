package com.asct94.securenote.data.di

import com.asct94.securenote.data.DefaultAuthRepository
import com.asct94.securenote.data.DefaultNotesRepository
import com.asct94.securenote.data.DefaultSettingsRepository
import com.asct94.securenote.domain.repositories.AuthRepository
import com.asct94.securenote.domain.repositories.NotesRepository
import com.asct94.securenote.domain.repositories.SettingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindNotesRepository(defaultNotesRepository: DefaultNotesRepository): NotesRepository

    @Binds
    abstract fun bindAuthRepository(defaultAuthRepository: DefaultAuthRepository): AuthRepository

    @Binds
    abstract fun bindSettingsRepository(defaultSettingsRepository: DefaultSettingsRepository): SettingsRepository
}
