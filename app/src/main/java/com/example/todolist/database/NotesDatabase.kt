package com.example.todolist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todolist.data.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)

abstract class NotesDatabase: RoomDatabase(){

    abstract fun getNoteDao(): NoteDAO

    companion object{
        private var INSTANCE: NotesDatabase? = null

        fun getDatabase(context: Context):NotesDatabase{
            return INSTANCE?:synchronized(this){          //creating a room database
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    "notes_database"

                ).build()
                INSTANCE = instance
                instance
            }

        }
    }
}