package com.example.todolist.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // Call superclass implementation

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.addfab.setOnClickListener {
            val intent = Intent(this, AddNote::class.java)
            startActivity(intent)
        }


    }
}