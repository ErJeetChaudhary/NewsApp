package com.example.jokeapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import com.example.jokeapp.config.SingletonHolder
import com.example.jokeapp.database.dao.JokeDao
import com.example.jokeapp.database.entities.Joke

/**
 * Created by Jitendra on 14/05/23.
 **/
@Database(entities = [Joke::class], version = 1, exportSchema = false)
abstract class JokeDatabase : RoomDatabase() {

    abstract fun newsDao(): JokeDao

    companion object : SingletonHolder<JokeDatabase, Context>({
        Room.databaseBuilder(it.applicationContext, JokeDatabase::class.java, "db_news")
            .allowMainThreadQueries()
            .addMigrations(*migrations)
            .build()
    })
}

private val migrations = arrayOf<Migration>()