package com.example.flightapp.ui.view

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightapp.data.Airport
import com.example.flightapp.data.FlightDatabase
import com.example.flightapp.data.ItemDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel( private val itemDao: ItemDao): ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    var searched by mutableStateOf("")

    fun inputSearch(name: String){
        searched = name
        var searchWord = if (name.isNotEmpty()) "%$name%" else ""
        viewModelScope.launch {
            _uiState.update {
                getAirports(searchWord).map{ HomeUiState(it)}.first()
            }
        }
    }

    private fun getAirports(name: String): Flow<List<Airport>> = itemDao.getAirport(name)
}

data class HomeUiState(val airportList: List<Airport> = listOf())