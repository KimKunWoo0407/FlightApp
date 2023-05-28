package com.example.flightapp.ui.view

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightapp.data.Airport
import com.example.flightapp.ui.AppViewModelProvider
import com.example.flightapp.ui.navigation.NavigationDestination
import com.example.flightapp.ui.theme.FlightAppTheme

object HomeDestination: NavigationDestination{
    override val route = "home"
    override val titleRes = 0
}

@Composable
fun HomeScreen(
    navigateToDetail: (String)->Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory =  AppViewModelProvider.Factory)
){
    val homeUiState by viewModel.uiState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {


        SearchInput(
            value = viewModel.searched,
            onValueChange = {viewModel.inputSearch(it)}
        )

//        OutlinedTextField(
//            modifier = modifier
//                .padding(vertical = 10.dp),
//            value = viewModel.searched,
//            onValueChange = {viewModel.inputSearch(it)},
//            enabled = true,
//            singleLine = true
//        )
        HomeBody(
            modifier = modifier.
                padding(15.dp),
            itemList = homeUiState.airportList,
            onItemClick = navigateToDetail
        )
    }
}

@Composable
fun HomeBody(
    itemList: List<Airport>,
    modifier: Modifier = Modifier,
    onItemClick: (String) -> Unit ={}
){

    LazyColumn(
        modifier = modifier
    ){
       items(
           items = itemList,
           key = { item -> item.id}
       ) {item->
            AirportItem(
                item = item,
                onItemClick = {onItemClick(it.iataCode)}
            )
       }
    }
}

@Composable
fun AirportItem(
    item: Airport,
    onItemClick: (Airport) -> Unit,
    modifier: Modifier = Modifier
){
    Row(modifier = modifier
        .fillMaxWidth()
        .clickable { onItemClick(item) }
        .padding(vertical = 10.dp)
    ){
        Text(
            text = item.iataCode,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = item.name,
            modifier = Modifier.padding(start = 5.dp),
            maxLines = 1
        )
    }
}

@Composable
fun SearchInput(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String)->Unit={},
){
    OutlinedTextField(
        modifier = modifier
            .padding(vertical = 10.dp),
        value = value,
        onValueChange = onValueChange,
        enabled = true,
        singleLine = true
    )
}

@Preview(showBackground = true)
@Composable
fun ItemPreview(){
    AirportItem(
        item =  Airport(0, "ABC", "abcde", 1), onItemClick = {}
    )
}