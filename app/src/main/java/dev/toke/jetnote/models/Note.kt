package dev.toke.jetnote.models

import java.time.LocalDate
import java.util.Date
import java.util.UUID

data class Note(val id: UUID = UUID.randomUUID(), var title: String, var description: String, var date: LocalDate = LocalDate.now())