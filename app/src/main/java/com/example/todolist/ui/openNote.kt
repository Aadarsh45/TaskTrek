package com.example.todolist.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.R
import com.example.todolist.data.Note
import com.example.todolist.databinding.ActivityOpenNoteBinding

class openNote : AppCompatActivity() {
    private lateinit var binding: ActivityOpenNoteBinding
    private  var selectedNote: Note?=null
    private lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOpenNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        selectedNote = intent.getSerializableExtra("SelectedNote") as Note
        binding.ivBack.setOnClickListener(){
            finish()
        }
        binding.ivEdit.setOnClickListener(){
            val editNoteIntent = Intent(this@openNote, EditNote::class.java)
            editNoteIntent.putExtra("SelectedNote",selectedNote)
            startActivity(editNoteIntent)
        }

        binding.tvNotesTitle.text = selectedNote?.title
        binding.tvNotesDes.text = selectedNote?.des



    }
}