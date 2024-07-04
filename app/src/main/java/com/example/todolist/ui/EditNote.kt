package com.example.todolist.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.R
import com.example.todolist.data.Note
import com.example.todolist.databinding.ActivityEditNoteBinding

class EditNote : AppCompatActivity() {
    lateinit var binding: ActivityEditNoteBinding
    private  var selectedNote: Note?=null
    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        selectedNote = intent.getSerializableExtra("SelectedNote") as Note?
        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        binding.ivBack.setOnClickListener {
           finish()
        }
        binding.etNotesTitle.setText(selectedNote!!.title)
        binding.etNotesDes.setText(selectedNote!!.des)
        binding.llNote.setBackgroundColor(selectedNote!!.color!!)

        binding.btnupdate.setOnClickListener {
            val edtNote = Note(selectedNote!!.id, binding.etNotesTitle.text.toString(),
                binding.etNotesDes.text.toString(),
                selectedNote!!.color)

            viewModel.updateNote(edtNote)
            Toast.makeText(this, "Note Updated", Toast.LENGTH_SHORT).show()
            val mainIntent = Intent(this@EditNote, MainActivity::class.java)
            startActivity(mainIntent)
            finish()

        }


    }
}