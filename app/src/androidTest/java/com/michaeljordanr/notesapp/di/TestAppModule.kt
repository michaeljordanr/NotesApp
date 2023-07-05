package com.michaeljordanr.notesapp.di

import android.app.Application
import androidx.room.Room
import com.michaeljordanr.notesapp.feature_note.data.data_source.NoteDatabase
import com.michaeljordanr.notesapp.feature_note.data.repository.NoteRepositoryImpl
import com.michaeljordanr.notesapp.feature_note.domain.repository.NoteRepository
import com.michaeljordanr.notesapp.feature_note.domain.usecase.AddNote
import com.michaeljordanr.notesapp.feature_note.domain.usecase.DeleteNote
import com.michaeljordanr.notesapp.feature_note.domain.usecase.GetNote
import com.michaeljordanr.notesapp.feature_note.domain.usecase.GetNotes
import com.michaeljordanr.notesapp.feature_note.domain.usecase.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.inMemoryDatabaseBuilder(app, NoteDatabase::class.java)
            .build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            getNote = GetNote(repository)
        )
    }
}