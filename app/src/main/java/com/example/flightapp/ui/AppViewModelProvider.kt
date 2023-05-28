package com.example.flightapp.ui

import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flightapp.FlightApplication
import com.example.flightapp.ui.view.HomeViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import com.example.flightapp.ui.view.DetailViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(
                flightApplication().database.itemDao()
            )
        }

        initializer {
            DetailViewModel(
                this.createSavedStateHandle(),
                flightApplication().database.itemDao()
            )
        }
    }
}

fun CreationExtras.flightApplication(): FlightApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as FlightApplication)