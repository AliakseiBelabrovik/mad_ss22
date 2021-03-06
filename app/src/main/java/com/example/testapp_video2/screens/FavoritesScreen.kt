package com.example.testapp_video2.screens.favorites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.models.Movie
import com.example.testapp_video2.viewmodels.FavoritesViewModel
import com.example.testapp_video2.navigation.MovieScreens
import com.example.testapp_video2.widgets.MovieRow

@Composable
fun FavoritesScreen(navController: NavController = rememberNavController(), viewModel: FavoritesViewModel) {
    Scaffold( topBar = {
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
                Text(text ="My Favorite Movies")
            }
        }
    } ) {
        MainContent(navController, viewModel.favoriteMovies)
    }
}

@Composable
fun MainContent(navController: NavController = rememberNavController(), favMovies: List<Movie>) {
    Surface(
        color = MaterialTheme.colors.background
    ) {
        LazyColumn {
            items( favMovies) { movie ->
                MovieRow(movie = movie, showFavoriteIcon = false) { movieId ->
                    navController.navigate("${MovieScreens.DetailScreen.value}/$movieId")
                }
            }
        }
    }
}