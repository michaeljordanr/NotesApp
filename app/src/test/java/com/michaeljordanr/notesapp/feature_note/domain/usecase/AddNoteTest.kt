package com.michaeljordanr.notesapp.feature_note.domain.usecase

import com.michaeljordanr.notesapp.feature_note.data.repository.FakeNoteRepository
import com.michaeljordanr.notesapp.feature_note.domain.model.InvalidNoteException
import com.michaeljordanr.notesapp.feature_note.domain.model.Note
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class AddNoteTest {

    private lateinit var addNote: AddNote
    private lateinit var getNote: GetNote
    private lateinit var fakeNoteRepository: FakeNoteRepository


    @Before
    fun setUp() {
        fakeNoteRepository = FakeNoteRepository()
        addNote = AddNote(fakeNoteRepository)
        getNote = GetNote(fakeNoteRepository)
    }

    @Test
    fun `Check the validation of title, is blank`() {
        val titleBlankNote = Note("", "test", 1, 1)

        assertThrows(
            "The title of the note can't be empty.",
            InvalidNoteException::class.java
        ) {
            runBlocking {
                addNote(titleBlankNote)
            }
        }
    }

    @Test
    fun `Check the validation of content, is blank`() {
        val contentBlankNote = Note("test", "", 1, 1)

        assertThrows(
            "the content of the note can't be empty.",
            InvalidNoteException::class.java
        ) {
            runBlocking {
                addNote(contentBlankNote)
            }
        }
    }

    @Test
    fun `Check adding a valid a note`() {
        val noteId = 99
        val validNote = Note("test", "test", 1, 1, noteId)

        runBlocking {
            addNote(validNote)
            val note = getNote(99)
            assertNotNull(note)
        }
    }
}