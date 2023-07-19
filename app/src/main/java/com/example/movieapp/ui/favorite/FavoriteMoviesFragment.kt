package com.example.movieapp.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleObserver
import com.example.movieapp.R
import com.example.movieapp.data.room.Movie
import com.example.movieapp.databinding.FragmentFavoriteMoviesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteMoviesFragment : Fragment(), LifecycleObserver, DefaultLifecycleObserver {
    private lateinit var binding: FragmentFavoriteMoviesBinding
    private val viewModel: FavoriteMoviesViewModel by viewModel()
    private lateinit var adapter: MovieAdapterView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_favorite_movies, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        lifecycle.addObserver(viewModel)
        viewModel.favoriteMovies.observe(viewLifecycleOwner) { movies ->
            updateRecyclerView(movies)
        }

        return binding.root
    }

    private fun updateRecyclerView(movies: List<Movie>) {
        if (::adapter.isInitialized) {
            adapter.movies = movies.toMutableList()
            adapter.notifyDataSetChanged()
        } else {
            adapter = MovieAdapterView(movies.toMutableList())
            binding.recyclerMovies.adapter = adapter
        }
    }

}