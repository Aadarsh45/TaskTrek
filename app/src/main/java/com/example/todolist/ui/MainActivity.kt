package com.example.todolist.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.data.INoteRVAdapter
import com.example.todolist.data.Note
import com.example.todolist.data.NoteRVAdapter
import com.example.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), INoteRVAdapter {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: NoteViewModel
    private lateinit var adapter: NoteRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // Call superclass implementation

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.addfab.setOnClickListener {
            val intent = Intent(this, AddNote::class.java)
            startActivity(intent)
        }
        binding.recyclerview.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        adapter = NoteRVAdapter(this, this)
        binding.recyclerview.adapter = adapter

        viewModel.allNotes.observe(this) {
            adapter.submitList(it)
        }


    }

    override fun onCardClicked(note: Note) {

    }
}