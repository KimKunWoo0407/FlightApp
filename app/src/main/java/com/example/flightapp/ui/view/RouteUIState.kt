package com.example.flightapp.ui.view

data class RouteUIState (
    val favoriteCode: Int,
    val departureCode: String,
    val departureName: String,
    val destinationCode: String,
    val destinationName: String,
    val userId: String? = null,
    val bookMarked: Boolean = false
)