package com.example.testapp_video2.screens.detailscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.models.Movie
import com.example.myapplication.models.getMovieById
import com.example.myapplication.models.getMovies
import com.example.testapp_video2.viewmodels.FavoritesViewModel
import com.example.testapp_video2.widgets.FavoriteIcon
import com.example.testapp_video2.widgets.HorizontalScrollableImageView
import com.example.testapp_video2.widgets.MovieRow

//movieId : String ? = getMovies()[0].id
@Composable
fun DetailScreen(navController: NavController = rememberNavController(), favViewModel : FavoritesViewModel, movieId : String? = getMovies()[0].id) {
    val movie = getMovieById( movieId = movieId )

    Scaffold(topBar = {
        TopAppBar() {
            Row {
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow back",
                    modifier = Modifier.clickable {
                        navController.navigateUp()
                        //navController.popBackStack()
                    }
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = movie.title)
            }
        }
    }) {
        MainContent(movie = movie, favViewModel = favViewModel)
    }
}

@Composable
fun MainContent( movie : Movie, favViewModel : FavoritesViewModel ) {
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
    ) {
        Column() {
            MovieRow(
                movie = movie,
                showFavoriteIcon = true,
                content = {
                    FavoriteIcon(movie = movie, isFavorite = favViewModel.isFavorite(movie = movie)) {
                        movie ->
                        if ( ! favViewModel.addMovieToFavorites( movie = movie ) ) favViewModel.removeMovie( movie = movie )
                    }
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Divider()
            Text(
                text = "Movie Images",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalScrollableImageView(movie = movie)
        }
    }
}