package com.michaeljordanr.notesapp.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.michaeljordanr.notesapp.ui.theme.BabyBlue
import com.michaeljordanr.notesapp.ui.theme.LightGreen
import com.michaeljordanr.notesapp.ui.theme.RedOrange
import com.michaeljordanr.notesapp.ui.theme.BabyGreen
import com.michaeljordanr.notesapp.ui.theme.Violet

@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, BabyGreen)
    }
}

class InvalidNoteException(message: String): Exception(message)