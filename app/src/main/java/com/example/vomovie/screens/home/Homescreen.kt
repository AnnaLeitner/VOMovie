package com.example.vomovie.screens.home

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.vomovie.models.Movie
import com.example.vomovie.models.getMovies
import com.example.vomovie.nav.MovieScreens
import com.example.vomovie.ui.theme.VOMovieTheme
import com.example.vomovie.viewModels.MovieViewModel
import com.example.vomovie.widgets.FavIcon
import com.example.vomovie.widgets.MovieRow


@Composable
fun HomeScreen(
    navController: NavController = rememberNavController(),
    movieViewModel: MovieViewModel = viewModel()
) {

    MyAppBar(navController = navController) {
        MainContent(navController = navController, movieViewModel = movieViewModel)
    }
}

@Composable
fun MainContent(
    navController: NavController,
    movies: List<Movie> = getMovies(),
    movieViewModel: MovieViewModel
) {
    LazyColumn {
        items(movies) { movie ->
            MovieRow(movie,
                onItemClick = { movieId ->// mit lambda habe ich zugriff auf id

                    Log.d("MainContent", "My callback value: $movieId")
                    navController.navigate(route = MovieScreens.DetailScreen.name + "/$movieId")
                },
                content = {
                    FavIcon(movie = movie, isFavItem = movieViewModel.isMovieFav(movie),

                        onFavClick = { favmovie ->
                            if (!movieViewModel.isMovieFav(favmovie)) {
                                movieViewModel.addMovie(favmovie)
                            } else {
                                movieViewModel.removeMovie(favmovie)
                            }
                        }
                    )
                })
        }
    }

}

@Composable
fun MyAppBar(navController: NavController, content: @Composable () -> Unit) {
    var showFavs by remember {
        mutableStateOf(false)
    }
    VOMovieTheme {
        Scaffold(topBar = {
            TopAppBar(
                title = { Text(text = "Movies") },
                actions = {
                    IconButton(onClick = { showFavs = !showFavs }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
                    }
                    DropdownMenu(expanded = showFavs, onDismissRequest = { showFavs = false }) {
                        DropdownMenuItem(onClick = { navController.navigate(route = MovieScreens.FavScreen.name) }) {
                            Row {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = "Favourites",
                                    modifier = Modifier.padding(4.dp)
                                )
                                Text(
                                    text = "Favourites",
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .width(100.dp)
                                )
                            }
                        }
                    }
                })
        }) {

            content()
        }
    }
}

