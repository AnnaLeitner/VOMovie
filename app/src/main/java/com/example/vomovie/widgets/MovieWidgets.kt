package com.example.vomovie.widgets

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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.vomovie.models.Movie
import com.example.vomovie.models.getMovies


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovieRow(
    movie: Movie,
    onItemClick: (String) -> Unit = {},
    //onFavClick: (Movie) -> Unit = { /*false*/ },
    content: @Composable () -> Unit = {},
    //favBoolean: Boolean,
    //showFavIcon: Boolean,
    //isFavItem: Boolean
    // content: @Composable() -> Unit = {}

) {

    var showDesc by remember { // show Description
        mutableStateOf(false)
    }
    var visible by remember { // show Description
        mutableStateOf(false)
    }
    var fav by remember { // show Description
        mutableStateOf(false)
    }


    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .heightIn(min = 130.dp) // hoehe mind 130.dp kann auch mehr sein
            .animateContentSize()
            .clickable {
                onItemClick(movie.id)
            },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),

                ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.images[0])
                        //.crossfade(true)
                        .build(),
                    contentDescription = "Profil Pic",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                )
            }

            Column {

                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = "Director: ${movie.director}",
                    style = MaterialTheme.typography.caption
                )
                Text(
                    text = "Released Year: ${movie.year}",
                    style = MaterialTheme.typography.caption
                )



                if (showDesc) {
                    AnimatedVisibility(
                        visible = showDesc,
                        modifier = Modifier
                            .padding(10.dp),
                        enter = slideInVertically(),
                        exit = slideOutVertically()

                    ) {

                        Column {
                            Text(
                                text = "Plot: ${movie.plot}",
                                style = MaterialTheme.typography.caption,
                            )
                            Divider(
                                modifier = Modifier.padding(5.dp)
                            )

                            Text(
                                text = "Genre: ${movie.genre}",
                                style = MaterialTheme.typography.caption
                            )
                            Text(
                                text = "Actors: ${movie.actors}",
                                style = MaterialTheme.typography.caption
                            )
                            Text(
                                text = "Rating: ${movie.rating}",
                                style = MaterialTheme.typography.caption
                            )
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = "Arrow Down",
                                modifier = Modifier.clickable { showDesc = !showDesc }
                            )
                        }

                    }
                } else {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "Arrow Up",
                        modifier = Modifier.clickable { showDesc = !showDesc }
                    )
                }
            }
            content()
        }
    }
}

@Composable
fun FavIcon(movie: Movie, onFavClick: (Movie) -> Unit = {}, isFavItem: Boolean) {

    Surface(
        modifier = Modifier
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.End
        ) {
            IconButton(onClick = {
                onFavClick(movie)
            }) {
                if (!isFavItem) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Favorites",
                        //modifier = Modifier.clickable { onFavClick(movie); fav = !fav },
                        tint = Color.Magenta
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorites",
                        //modifier = Modifier.clickable {},
                        tint = Color.Magenta
                    )
                }
            }
        }

    }
}


@Composable
fun HorizontalScrollImages(movie: Movie = getMovies()[0]) {
    LazyRow {
        items(movie.images) { image ->

            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .height(250.dp),
                elevation = 4.dp
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(image)
                        //.crossfade(true)
                        .build(),
                    contentDescription = "Movie Images",
                    modifier = Modifier.clip(RoundedCornerShape(5.dp))
                )

            }
        }
    }
}
