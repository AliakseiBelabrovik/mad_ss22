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
import com.example.myapplication.models.getMovies
import com.example.testapp_video2.navigation.MovieScreens
import com.example.testapp_video2.widgets.MovieRow

@Composable
fun FavoritesScreen(navController: NavController = rememberNavController()) {
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
        MainContent(navController)
    }
}

@Composable
fun MainContent(navController: NavController = rememberNavController()) {
    val movies = listOf<Movie>(getMovies()[5], getMovies()[8], getMovies()[3], getMovies()[6] )
    Surface(
        color = MaterialTheme.colors.background
    ) {
        LazyColumn {
            items( movies) { movie ->
                MovieRow(movie = movie) { movieId ->
                    navController.navigate("${MovieScreens.DetailScreen.value}/$movieId")
                }
            }
        }
    }
}