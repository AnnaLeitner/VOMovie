package com.example.vomovie.viewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.vomovie.models.Movie

class MovieViwModel : ViewModel(){
    private var _favMovies = mutableStateListOf<Movie>()
    val favMovie : List<Movie>
        get() = _favMovies

    fun addMovie(movie: Movie){
        if(!exists(movie = movie)) {
            _favMovies.add(movie)
        }
    }
    fun removeMovie(movie:Movie){
        _favMovies.remove(movie)
    }
    fun getAllMovies(): List<Movie>{
        return _favMovies
    }
    fun checkMovie(movie: Movie){
        _favMovies.contains(movie)
    }

    private fun exists(movie : Movie) : Boolean{
        return _favMovies.any { m -> m.id == movie.id }
    }
    fun isFav(movie: Movie) : Boolean{
        return exists(movie)
    }
}