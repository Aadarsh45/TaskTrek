package com.example.todolist.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.Note
import com.example.todolist.data.NoteRepository
import com.example.todolist.database.NotesDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



//creating of a viewmodel
class NoteViewModel(application: Application): AndroidViewModel(application) {
    val allNotes: LiveData<List<Note>>

    private val repository: NoteRepository

    init {
        val dao = NotesDatabase.getDatabase(application).getNoteDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    fun addNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)

    }

    fun removeNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)


    }

    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }


}