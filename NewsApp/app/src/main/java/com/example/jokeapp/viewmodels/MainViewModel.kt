package com.example.jokeapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.jokeapp.database.entities.Joke
import com.example.jokeapp.repository.JokeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Jitendra on 04/01/23.
 **/
@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val repository: JokeRepository
) : AndroidViewModel(application) {

    private var job: Job? = null

    val errorLiveData = MutableLiveData<String>()

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        errorLiveData.postValue("Internet connection is not available. Please retry")
    }

    init {
        fetchJokes()
    }

    fun getJokes(): LiveData<PagingData<Joke>> = Pager(PagingConfig(pageSize = 10)) { repository.getJokes() }.liveData

    private fun fetchJokes() {
        job = viewModelScope.launch(exceptionHandler) {
            while (NonCancellable.isActive) {
                val joke = repository.fetchJoke()
                repository.insert(joke)
                delay(60000)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}