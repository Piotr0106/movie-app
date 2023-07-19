package com.example.movieapp.ui.core

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.movieapp.ui.movie.MovieViewModel.Status
import com.squareup.picasso.Picasso

@BindingAdapter("android:progress")
fun setProgressVisibility(view: View, visible: Status) {
    view.visibility =
        when (visible) {
            Status.Loading -> View.VISIBLE
            Status.Success, Status.Error -> View.GONE
        }
}

@BindingAdapter("android:visibility")
fun setVisibility(view: View, visible: Status) {
    view.visibility =
        when (visible) {
            Status.Success -> View.VISIBLE
            Status.Loading, Status.Error -> View.GONE
        }
}

@BindingAdapter("android:imageUrl", "android:imageDefault")
fun loadImage(imageView: ImageView, url: String, placeholder: Drawable) {
    if (url == null) {
        imageView.setImageDrawable(placeholder)
    } else {
        Picasso.get()
            .load(url)
            .into(imageView)
    }
}
