package com.example.jokeapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.lifecycleScope
import com.example.jokeapp.R
import com.example.jokeapp.databinding.ActivityMainBinding
import com.example.jokeapp.ui.adapters.JokesAdapter
import com.example.jokeapp.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    private val viewModel by viewModels<MainViewModel>()

    private val adapter by lazy { JokesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.recyclerView.adapter = adapter
        viewModel.getJokes().observe(this) {
            lifecycleScope.launch { adapter.submitData(it) }
        }
        viewModel.errorLiveData.distinctUntilChanged().observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
    }
}