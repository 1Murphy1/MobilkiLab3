package com.example.rickandmorty

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.adapter.CharacterAdapter
import com.example.rickandmorty.viewmodel.CharacterViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var characterAdapter: CharacterAdapter
    private val viewModel: CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)

        recyclerView.layoutManager = LinearLayoutManager(this)
        characterAdapter = CharacterAdapter(emptyList())
        recyclerView.adapter = characterAdapter

        observeViewModel()
        viewModel.loadCharacters()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.isLoading.collectLatest { isLoading ->
                progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        }

        lifecycleScope.launch {
            viewModel.characters.collectLatest { characters ->
                characterAdapter = CharacterAdapter(characters)
                recyclerView.adapter = characterAdapter
                recyclerView.visibility = if (characters.isEmpty()) View.GONE else View.VISIBLE
            }
        }

        lifecycleScope.launch {
            viewModel.errorMessage.collectLatest { errorMessage ->
                if (errorMessage != null) {
                    Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_LONG).show()
                    recyclerView.visibility = View.GONE
                }
            }
        }
    }
}
