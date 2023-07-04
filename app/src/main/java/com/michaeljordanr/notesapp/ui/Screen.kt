package com.michaeljordanr.notesapp.ui

sealed class Screen(val route: String) {
    object NotesScreen: Screen("notes_screen")
    object AddEditNoteScreen: Screen("add_edit_Screen")
}
