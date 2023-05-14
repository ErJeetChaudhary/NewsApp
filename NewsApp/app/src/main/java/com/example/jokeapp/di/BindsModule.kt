package com.example.jokeapp.di

import com.example.jokeapp.repository.JokeRepository
import com.example.jokeapp.repository.impl.JokeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Jitendra on 04/01/23.
 **/
@Module
@InstallIn(SingletonComponent::class)
interface BindsModule {

    @Singleton
    @Binds
    fun provideJokeRepository(repositoryImpl: JokeRepositoryImpl): JokeRepository

}