package com.example.todolist.data

import androidx.lifecycle.LiveData
import com.example.todolist.database.NoteDAO

//Repository
class NoteRepository(private val noteDao: NoteDAO) {

    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note) = noteDao.insertNote(note)
    suspend fun delete(note: Note) = noteDao.deleteNote(note)
    suspend fun update(note: Note) = noteDao.updateNote(note)

}