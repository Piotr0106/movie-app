package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.ui.favorite.FavoriteMoviesFragment
import com.example.movieapp.ui.movie.MovieFragment
import com.example.movieapp.ui.ViewPagerAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val fragmentList = listOf(MovieFragment(), FavoriteMoviesFragment())
        val adapter = ViewPagerAdapter(fragmentList, supportFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter
        setContentView(binding.root)
    }
}