package com.example.jokeapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jokeapp.R
import com.example.jokeapp.database.entities.Joke
import com.example.jokeapp.databinding.ViewHolderJokeBinding

/**
 * Created by Jitendra on 14/05/23.
 **/
class JokesAdapter : PagingDataAdapter<Joke, JokesAdapter.ViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ViewHolderJokeBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ViewHolderJokeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(joke: Joke?) {
            if (joke == null) return
            binding.textView.text = binding.root.context.getString(R.string.input_joke_id, joke.id, joke.joke)
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Joke>() {

            override fun areItemsTheSame(oldItem: Joke, newItem: Joke) = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Joke, newItem: Joke) = oldItem.id == newItem.id
        }
    }
}