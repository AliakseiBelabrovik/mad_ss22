package com.example.testapp_video2.screens.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.models.Movie
import com.example.myapplication.models.getMovies
import com.example.testapp_video2.navigation.MovieScreens
import com.example.testapp_video2.widgets.MovieRow

@Composable
fun HomeScreen( navController: NavController = rememberNavController()) {
    var showMenu by remember {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Movies") },
                actions = {
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More" )
                    }
                    DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                        DropdownMenuItem(onClick = {
                            navController.navigate(MovieScreens.FavoritesScreen.value)
                        }) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = "Favorites",
                                    modifier = Modifier.padding(4.dp)
                                    /*
                                        .clickable {
                                            navController.navigate(MovieScreens.FavoritesScreen.value)
                                        }
                                     */
                                )
                                Text(
                                    text = "Favorites",
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .width(125.dp)
                                )
                            }
                        }
                    }
                }
            )
        }
    ) {
        MainContent(getMovies(), navController = navController)
    }
}

@Composable
fun MainContent( movieList : List<Movie>, navController: NavController ) {
    Surface(
        color = MaterialTheme.colors.background
    ) {
        LazyColumn {
            items( movieList) { movie ->
                MovieRow(movie = movie) { movieId ->
                    navController.navigate("${MovieScreens.DetailScreen.value}/$movieId")
                }
           }
        }
    }
}
