package com.example.movieapp.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Movie::class], version = 2, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        private var instance: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    MovieDatabase::class.java,
                    "favorite_movies_table"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }

        fun deleteInstanceOfDatabase() {
            instance = null
        }
    }
}