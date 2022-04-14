package com.example.vomovie.viewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.vomovie.models.Movie

class MovieViewModel : ViewModel() {
    private var _favouriteMovies = mutableStateListOf<Movie>()
    val favouriteMovie: List<Movie>
        get() = _favouriteMovies

    fun addMovie(movie: Movie) {
        if (!exists(movie = movie)) {
            _favouriteMovies.add(movie)
        }
    }

    fun removeMovie(movie: Movie) {
        _favouriteMovies.remove(movie)
    }

    fun getAllMovies(): List<Movie> {
        return _favouriteMovies
    }

    fun checkMovie(movie: Movie): Boolean {
        return _favouriteMovies.contains(movie)
    }

    private fun exists(movie: Movie): Boolean {
        return _favouriteMovies.any { m -> m.id == movie.id }
    }

    fun isFav(movie: Movie): Boolean {
        return exists(movie)
    }
}