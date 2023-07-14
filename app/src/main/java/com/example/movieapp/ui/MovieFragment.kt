package com.example.movieapp.ui

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
        viewModel = MovieViewModel()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.movieImage.observe(viewLifecycleOwner) { imageUrl ->
            Picasso.get().load(imageUrl).into(binding.movieImage)
        }

        return binding.root
    }



}