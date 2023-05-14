package com.example.jokeapp.repository.impl

import androidx.paging.PagingSource
import com.example.jokeapp.apis.JokeServices
import com.example.jokeapp.database.dao.JokeDao
import com.example.jokeapp.database.entities.Joke
import com.example.jokeapp.repository.JokeRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Jitendra on 04/01/23.
 **/
@Singleton
class JokeRepositoryImpl @Inject constructor(
    private val dao: JokeDao,
    private val jokeServices: JokeServices
) : JokeRepository {

    override suspend fun insert(vararg joke: Joke): List<Long> = dao.insertJoke(*joke)

    override suspend fun fetchJoke(): Joke = jokeServices.getJoke(JokeServices.URL_JOKE)

    override fun getJokes(): PagingSource<Int, Joke> = dao.getJokes()
}