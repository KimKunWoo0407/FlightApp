package com.example.flightapp.ui.view

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightapp.data.Airport
import com.example.flightapp.data.ItemDao
import com.example.flightapp.data.Route
import com.example.flightapp.data.Selected
import com.example.flightapp.data.currentUserId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val itemDao: ItemDao
) : ViewModel(){

    private var itemCode: String = checkNotNull(savedStateHandle[DetailDestination.itemIdArg])

    var searched by mutableStateOf(itemCode)

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    init {
        inputSearch(searched)
    }

    fun inputSearch(code: String){
        searched = code
        viewModelScope.launch {
            _uiState.update {
                getRouteWithCode(searched).map{ DetailUiState(it)}.first()
            }
        }
    }

    suspend fun bookMark(route: Route){
        val item = Selected(userId = currentUserId, favoriteCode = route.favoriteCode)
        if(route.userId==null)
            itemDao.bookMark(item)
        else
            itemDao.delete(item)

    }

    private fun getRouteWithCode(code: String): Flow<List<Route>> = itemDao.getRouteWithCode(code)
}

data class DetailUiState(val routeList: List<Route> = listOf())