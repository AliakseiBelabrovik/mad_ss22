package com.example.testapp_video2.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.models.Movie

class FavoritesViewModel : ViewModel() {

    private val _favoriteMovies = mutableStateListOf<Movie>() //is mutableListOf<Movie>()
    val favoriteMovies : List<Movie>
        get() = _favoriteMovies

    fun addMovieToFavorites( movie: Movie ) : Boolean {
        return if (!exists( movie = movie )) {
            this._favoriteMovies.add( movie );
            true
        } else {
            false
        }
    }

    fun removeMovie(movie: Movie ) : Boolean {
        return _favoriteMovies.removeIf { m -> m.id == movie.id  }
    }

    fun isFavorite( movie: Movie ) : Boolean {
        return exists( movie );
    }

    private fun exists(movie: Movie) : Boolean {
        return _favoriteMovies.any { m -> m.id == movie.id }
    }
}