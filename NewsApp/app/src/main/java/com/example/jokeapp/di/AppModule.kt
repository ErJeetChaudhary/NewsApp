package com.example.jokeapp.di

import android.content.Context
import com.example.jokeapp.apis.JokeServices
import com.example.jokeapp.database.JokeDatabase
import com.example.jokeapp.database.dao.JokeDao
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by Jitendra on 14/05/23.
 **/
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): JokeDatabase = JokeDatabase.getInstance(context)

    @Singleton
    @Provides
    fun provideJokeDao(database: JokeDatabase): JokeDao = database.newsDao()

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
        return Retrofit.Builder()
            .baseUrl("https://geek-jokes.sameerkumar.website")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideJokeServices(retrofit: Retrofit): JokeServices = retrofit.create(JokeServices::class.java)

}