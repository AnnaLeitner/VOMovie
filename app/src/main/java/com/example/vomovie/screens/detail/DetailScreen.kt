package com.example.vomovie.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.vomovie.models.Movie

import com.example.vomovie.models.getMovies
import com.example.vomovie.widgets.HorizontalScrollImages
import com.example.vomovie.widgets.MovieRow

@Preview(showBackground = true)
@Composable
fun DetailScreen(
    navController: NavController = rememberNavController(),
    movieId: String? = "t0499549"
    ){

    val movie = movieFilter(movieId = movieId)


    Scaffold(
        topBar = {
            TopAppBar(elevation = 3.dp){
                Row {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow Back",
                        modifier = Modifier.clickable {
                            navController.popBackStack() //go back
                        }
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = movie.title)
                }
            }
        }) {
        MainContent(movie = movie)
    }
}

@Composable
fun MainContent(movie: Movie){

    Surface(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            MovieRow(movie = movie)

            Spacer(modifier = Modifier.height(8.dp))
            Divider()
            Text(text = "Movie Images",
                //textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5
            )

            HorizontalScrollImages(movie = movie)

        }

    }

}

fun movieFilter(movieId: String?) : Movie {
    return getMovies().filter {movie -> movie.id == movieId }[0]
}