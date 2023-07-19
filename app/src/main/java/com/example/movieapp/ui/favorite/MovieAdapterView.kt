package com.example.movieapp.ui.favorite

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.data.room.Movie
import com.example.movieapp.databinding.MovieItemBinding
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MovieAdapterView(var movies: MutableList<Movie>) : RecyclerView.Adapter<MyViewHolder>() {
    private lateinit var binding: MovieItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val layoutInflater = LayoutInflater.from(parent.context)
        val movieItemBinding = MovieItemBinding.inflate(layoutInflater, parent, false)

        return MyViewHolder(movieItemBinding)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie, this, position)
    }
}

class MyViewHolder(private val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root), KoinComponent{
    private val viewModel: FavoriteMoviesViewModel by inject()

    @SuppressLint("CheckResult")
    fun bind(movie: Movie, adapterView: MovieAdapterView, position: Int) {
        binding.movieItemText.text = movie.title
        binding.deleteMovieButton.setOnClickListener {
            viewModel.removeMovieFromFavorites(movie)
            adapterView.notifyItemRemoved(position)
            adapterView.movies.removeAt(position)
            viewModel.getFavoriteMoviesList()
        }
        binding.executePendingBindings()
    }

}