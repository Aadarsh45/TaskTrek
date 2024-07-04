package com.example.todolist.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchUIUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.data.INoteRVAdapter
import com.example.todolist.data.Note
import com.example.todolist.data.NoteRVAdapter
import com.example.todolist.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

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
        swipeToDelete()


    }
    private fun swipeToDelete(){
        val swipeToDelete = object:ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
                return true;
            }


            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                TODO("Not yet implemented")
                val position = viewHolder.adapterPosition
                val currList = adapter.currentList.toMutableList()
                currList.removeAt(position)
                viewModel.removeNote(adapter.currentList[position])
                adapter.submitList(currList)

                val snackbar = Snackbar.make(binding.root, "Note Deleted", Snackbar.LENGTH_LONG)
                snackbar.setAction("Undo"){
                    val newcurr = adapter.currentList.toMutableList()
                    newcurr.add(position, currList[position])
                    viewModel.addNote(adapter.currentList[position])
                    adapter.submitList(newcurr)
                }
                snackbar.setActionTextColor(resources.getColor(R.color.yellow))
                snackbar.show()

            }

        }
        val itemTouchHelper = ItemTouchHelper(swipeToDelete)
        itemTouchHelper.attachToRecyclerView(binding.recyclerview)

    }

    override fun onCardClicked(note: Note) {
        val openNoteIntent = Intent(this@MainActivity, openNote::class.java)
        openNoteIntent.putExtra("SelectedNote",note)
        startActivity(openNoteIntent)
    }
}