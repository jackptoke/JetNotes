package dev.toke.jetnote.datas

import dev.toke.jetnote.models.Note

class NoteData {
    fun loadNotes(): List<Note> {
        return listOf(
            Note(title="Have a good one", description="A greeting of no meaning"),
            Note(title="Good morning", description="A greeting for the morning"),
            Note(title="Good afternoon", description="A greeting for the afternoon"),
            Note(title="Good evening", description="A greeting for the evening"),
            Note(title="Good night", description="A last well wish for the night"),
            Note(title="Goodbye", description="Wishing you well, before departing"),
        )
    }
}