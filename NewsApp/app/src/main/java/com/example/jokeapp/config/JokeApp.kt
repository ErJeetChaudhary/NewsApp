package com.example.jokeapp.config

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by Jitendra on 14/05/23.
 **/
@HiltAndroidApp
class JokeApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

}