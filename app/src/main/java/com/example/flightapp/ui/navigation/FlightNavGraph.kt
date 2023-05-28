package com.example.flightapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.flightapp.ui.view.DetailDestination
import com.example.flightapp.ui.view.DetailScreen
import com.example.flightapp.ui.view.HomeDestination
import com.example.flightapp.ui.view.HomeScreen

@Composable
fun FlightNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = HomeDestination.route
    ){
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToDetail = {navController.navigate("${DetailDestination.route}/$it")}
            )
        }
        composable(
            route = DetailDestination.routeWithArgs,
            arguments = listOf(navArgument(DetailDestination.itemIdArg){
                type = NavType.StringType
            })
        ){
            DetailScreen(

            )
        }

    }
}