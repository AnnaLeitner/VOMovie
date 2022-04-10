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
import com.example.vomovie.viewModels.MovieViwModel
import com.example.vomovie.widgets.MovieRow


@Composable
fun HomeScreen(
    navController: NavController = rememberNavController(), myViewModel: MovieViwModel = viewModel()){

    MyAppBar(navController = navController) {
        MainContent(navController = navController)
    }
}

@Composable
fun MainContent(navController: NavController, movies: List<Movie> = getMovies()){
    LazyColumn{
        items(items = movies){ movie ->
            MovieRow( movie = movie){ movieId ->// mit lambda habe ich zugriff auf id
                Log.d("MainContent", "My callback value: $movieId")
                navController.navigate(route = MovieScreens.DetailScreen.name + "/$movieId")
            }
            //navController.navigate(route = MovieScreens.FavScreen.name)
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
                        DropdownMenuItem(onClick = { navController.navigate(route = MovieScreens.FavScreen.name ) }) {
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
            /*val movies = getMovies()
            MainContent(movies)*/
        }
    }
}

