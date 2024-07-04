package com.example.todolist.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.R
import com.example.todolist.data.Note
import com.example.todolist.databinding.ActivityAddNoteBinding
import com.google.android.material.snackbar.Snackbar

class AddNote : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        binding.ivBack.setOnClickListener {
            finish()
        }
        binding.btnAdd.setOnClickListener {
            val noteTitle = binding.etNoteTittle.text.toString()
            val noteDesc = binding.etNoteDesc.text.toString()
            val colorArray = resources.getIntArray(R.array.cardColors)
            val randomColor = colorArray.random()

            if (noteTitle.isNotEmpty() && noteDesc.isNotEmpty()) {
                viewModel.addNote(Note(0,noteTitle, noteDesc, randomColor))
                finish()
            }
            else{
                Snackbar.make(binding.root, "Please enter some data", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}