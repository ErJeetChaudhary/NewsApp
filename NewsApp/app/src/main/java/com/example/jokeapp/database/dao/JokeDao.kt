package com.example.jokeapp.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.jokeapp.database.entities.Joke

/**
 * Created by Jitendra on 14/05/23.
 **/
@Dao
abstract class JokeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(vararg joke: Joke): List<Long>

    @Query("SELECT COUNT(id) FROM table_joke")
    abstract suspend fun getRowCount(): Int

    @Query("SELECT * FROM table_joke ORDER BY id DESC")
    abstract fun getJokes(): PagingSource<Int, Joke>

    @Query("DELETE FROM table_joke WHERE id NOT IN (SELECT id FROM table_joke ORDER BY id DESC LIMIT 9)")
    abstract fun deleteOthers(): Int

    @Transaction
    open suspend fun insertJoke(vararg joke: Joke): List<Long> {
        val count = getRowCount()
        if (count >= 10) deleteOthers()
        return insert(*joke)
    }
}