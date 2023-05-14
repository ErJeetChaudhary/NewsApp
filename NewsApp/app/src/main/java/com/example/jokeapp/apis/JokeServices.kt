package com.example.jokeapp.apis

import com.example.jokeapp.database.entities.Joke
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created by Jitendra on 14/05/23.
 **/
interface JokeServices {

    @GET
    suspend fun getJoke(@Url url: String): Joke


    companion object {

        const val URL_JOKE = "https://geek-jokes.sameerkumar.website/api?format=json"

    }

}