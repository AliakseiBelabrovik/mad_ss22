package com.example.testapp_video2.screens.detailscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapplication.models.Movie
import com.example.myapplication.models.getMovieById
import com.example.myapplication.models.getMovies
import com.example.testapp_video2.widgets.HorizontalScrollableImageView
import com.example.testapp_video2.widgets.MovieRow

//movieId : String ? = getMovies()[0].id
@Composable
fun DetailScreen( navController: NavController = rememberNavController(), movieId : String? = getMovies()[0].id) {
    val movie = getMovieById( movieId = movieId )

    var showMenu by remember {
        mutableStateOf(false) //menu is normalerweise zugeklappt
        //wir wollen dropdownmenu anzeigen, wen 3 punkte angeklickt werden
    }

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
                //Platzhalter
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = movie.title)
            }
        }
    }) {
        MainContent(movie = movie)
    }

    /*
    //if movie not null
    movie?.let {
        MainContent( navController = navController, movie = movie) {
            MovieRow(movie = movie)
            //Spacer(modifier = Modifier.height(8.dp))
            //Divider()
            //Text(text = "${movie.title}", style = MaterialTheme.typography.h5)
        }
    }

     */
}

@Composable
fun MainContent( movie : Movie) {
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
    ) {
        Column() {
            MovieRow(movie = movie)
            //Spacer(modifier = Modifier.height(8.dp))
            //Divider()
            //Text(text = "${movie.title}", style = MaterialTheme.typography.h5)
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