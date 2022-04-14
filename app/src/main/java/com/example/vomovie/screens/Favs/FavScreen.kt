package com.example.vomovie.screens.Favs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.vomovie.models.Movie
import com.example.vomovie.models.getMovies
import com.example.vomovie.nav.MovieScreens
import com.example.vomovie.viewModels.MovieViewModel
import com.example.vomovie.widgets.MovieRow

@Composable
fun FavScreen(
    navController: NavController = rememberNavController(),
    movieViewModel: MovieViewModel = viewModel()
) {

    val movies = movieViewModel.favouriteMovie

    Scaffold(
        topBar = {
            TopAppBar(elevation = 3.dp) {
                Row {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow Back",
                        modifier = Modifier.clickable {
                            navController.popBackStack() //go back
                        }
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = "My Favourie Movies")
                }
            }
        }) {
        MainContent(movies = movies, navController = navController, movieViewModel = movieViewModel)
    }

}

@Composable
fun MainContent(
    movies: List<Movie>,
    navController: NavController,
    movieViewModel: MovieViewModel
) {

    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        LazyColumn {
            items(movies) { movie ->
                MovieRow(movie,
                    onItemClick = { movieId ->
                        navController.navigate(route = MovieScreens.DetailScreen.name + "/$movieId")
                    })
                Spacer(modifier = Modifier.height(28.dp))


            }
            //navController.navigate(route = MovieScreens.FavScreen.name)
        }
    }


}
