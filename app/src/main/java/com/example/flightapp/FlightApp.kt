package com.example.flightapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.flightapp.ui.navigation.FlightNavHost

@Composable
fun FlightApp(navController: NavHostController = rememberNavController()) {
    FlightNavHost(navController = navController)
}

