package com.example.vomovie.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.example.vomovie.models.Movie
import com.example.vomovie.models.getMovies
import com.example.vomovie.ui.theme.VOMovieTheme


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovieRow(movie: Movie = getMovies()[0],
             onItemClick: (String) -> Unit = {}) {
//New
    var showDesc by remember { // show Description
        mutableStateOf(false)
    }

        Column() {
            Surface(
                color = MaterialTheme.colors.background,

                ) {

                Card(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                        .fillMaxHeight()
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
                            shape = RectangleShape,
                            elevation = 6.dp
                        ) {
                            Icon(
                                imageVector = Icons.Default.AccountBox,
                                contentDescription = "profile pic"
                            )
                        }
                        Column() {


                            Text(
                                text = movie.title,

                                )
                            Text(
                                text = "Director: ${movie.director}",
                                style = MaterialTheme.typography.caption
                            )
                            Text(
                                text = "Year: ${movie.year}",
                                style = MaterialTheme.typography.caption
                            )
                            //New
                            if (showDesc) {
                                AnimatedVisibility(
                                    visible = showDesc,
                                    modifier = Modifier
                                        .padding(10.dp),
                                    enter = slideInVertically(
                                    ),
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
                    }
                }
            }
        }

}
