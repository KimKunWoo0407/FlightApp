package com.example.flightapp

import android.app.Application
import com.example.flightapp.data.FlightDatabase

class FlightApplication: Application() {
    val database: FlightDatabase by lazy{
        FlightDatabase.getDatabase(this)
    }
}