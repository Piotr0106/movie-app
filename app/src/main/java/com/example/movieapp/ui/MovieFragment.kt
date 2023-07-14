package com.example.movieapp.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentMovieBinding
import com.squareup.picasso.Picasso

class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding
    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_movie, container, false
        )
        val imageUrl =
            "https://m.media-amazon.com/images/M/MV5BMDg2NzQwOGMtMGRkNC00YjAwLTg4NjgtZWQwYzljZmM1YzA4XkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_.jpg"
        Picasso.get().load(imageUrl).into(binding.movieImage);

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
    }

}