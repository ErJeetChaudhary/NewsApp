package com.example.jokeapp.repository

import androidx.paging.PagingSource
import com.example.jokeapp.database.entities.Joke

/**
 * Created by Jitendra on 14/05/23.
 **/
interface JokeRepository {

    suspend fun insert(vararg joke: Joke): List<Long>

    suspend fun fetchJoke(): Joke

    fun getJokes(): PagingSource<Int, Joke>

}