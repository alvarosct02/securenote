package com.asct94.securenote.data.di

import android.content.Context
import androidx.room.Room
import com.asct94.securenote.data.local.room.AppDatabase
import com.asct94.securenote.data.local.room.dao.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory


@InstallIn(SingletonComponent::class)
@Module
object RoomDatabaseModule {

    @Provides
    fun provideNoteDao(database: AppDatabase): NoteDao {
        return database.noteDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        val builder = Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "secureNote.db"
        )
        val factory = SupportFactory(
            SQLiteDatabase.getBytes(
                AppDatabase::class.qualifiedName.orEmpty().toCharArray()
            )
        )
        builder.openHelperFactory(factory)
        return builder.build()
    }
}