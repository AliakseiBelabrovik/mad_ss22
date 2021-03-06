package com.example.testapp_video2.widgets

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapplication.models.Movie
import com.example.testapp_video2.ui.theme.Turquoise

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovieRow(
    movie : Movie,
    showFavoriteIcon : Boolean,
    content : @Composable () -> Unit = {},
    onClickItem: ( String ) -> Unit = {},
) {
    var showHiddenInfo by remember {
        mutableStateOf(false)
    }

    Card(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
        //.height(if (showHiddenInfo) 250.dp else 130.dp)
        .clickable {
            onClickItem(movie.id)
        },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                    //shape = RectangleShape,
                    //elevation = 6.dp
                ) {
                    //Icon(imageVector = Icons.Default.AccountBox, contentDescription = "Profile picture")
                    AsyncImage(
                        model = movie.images[0],
                        contentDescription = "Movie poster",
                        modifier = Modifier.clip(CircleShape),
                        contentScale = ContentScale.Crop,
                    )
                }
                Column() {
                    Text(text = movie.title, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                    Text(text = "Director: ${movie.director}", fontSize = 12.sp)
                    Text(text = "Released: ${movie.year}", fontSize = 12.sp)
                    AnimatedVisibility(
                        visible = showHiddenInfo
                    ) {
                        Text(
                            text = "Plot: ${movie.plot}", fontSize = 12.sp,
                        )
                    }
                    AnimatedVisibility(
                        visible = showHiddenInfo) {
                        Divider(color = Color.LightGray, thickness = 1.dp,
                            modifier = Modifier.padding(2.dp)
                        )
                    }
                    AnimatedVisibility(
                        visible = showHiddenInfo
                    ) {
                        Text(
                            text = "Genre: ${movie.genre}", fontSize = 12.sp,
                        )
                    }
                    AnimatedVisibility(
                        visible = showHiddenInfo
                    ) {
                        Text(
                            text = "Actors: ${movie.actors}", fontSize = 12.sp,
                        )
                    }
                    AnimatedVisibility(
                        visible = showHiddenInfo
                    ) {
                        Text(
                            text = "Rating: ${movie.rating}", fontSize = 12.sp,
                        )
                    }
                    if (showHiddenInfo)
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = "Hide additional information",
                            modifier = Modifier.clickable { showHiddenInfo = !showHiddenInfo }
                        )
                    else
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "Show hidden information",
                            modifier = Modifier.clickable { showHiddenInfo = !showHiddenInfo }
                        )
                }
            }
            AnimatedVisibility(
                visible = showFavoriteIcon
            ) {
                content()
            }
        }
    }
}

@Composable
fun HorizontalScrollableImageView( movie : Movie ) {
    LazyRow {
        items(movie.images) { image ->
            Card(
                modifier = Modifier.padding(12.dp),
                elevation = 4.dp
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data( image )
                        .crossfade(true)
                        .build(),
                    contentDescription = "Image number " + movie.images.indexOf(image) + " of " + movie.title
                )
            }
        }
    }
}

@Composable
fun FavoriteIcon(
    movie: Movie,
    isFavorite: Boolean,
    onFavIconClick: (Movie) -> Unit = {}
) {
    Icon(
        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
        contentDescription = "Add to Favorites",
        modifier = Modifier
            .padding(end = 25.dp)
            .clickable {
                onFavIconClick(movie)
            },
        tint = Turquoise
    )
}

