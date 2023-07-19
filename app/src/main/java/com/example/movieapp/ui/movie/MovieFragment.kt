package com.example.movieapp.ui.movie

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentMovieBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {
    private lateinit var binding: FragmentMovieBinding
    private val viewModel: MovieViewModel by viewModel()

    @SuppressLint("CheckResult")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_movie, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.events
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { handleEvent(it) }
        return binding.root
    }

    private fun handleEvent(event: MovieViewModel.Event){
        when(event){
            is MovieViewModel.Event.OnAddToFavoritesClick -> {
                val favoriteButton = binding.favoriteButton
                viewModel.isFavorite.observe(viewLifecycleOwner) { isFavorite ->
                    if (isFavorite) {
                        favoriteButton.setImageResource(R.drawable.favorite)
                    } else {
                        favoriteButton.setImageResource(R.drawable.favorite_border)
                    }
                }
            }
        }
    }

}