package com.example.flightapp.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightapp.data.Route
import com.example.flightapp.data.currentUserId
import com.example.flightapp.ui.AppViewModelProvider
import com.example.flightapp.ui.navigation.NavigationDestination

object DetailDestination : NavigationDestination{
    override val route = "detail"
    override val titleRes = 1
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(factory =  AppViewModelProvider.Factory)
){

    val detailUiState by viewModel.uiState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        SearchInput(
            value = viewModel.searched,
            onValueChange = {viewModel.inputSearch(it)}
        )
        DetailBody(
            itemList = detailUiState.routeList,
            onItemClick = {viewModel.bookMark(it)},
            modifier = modifier.
            padding(15.dp),
        )
    }
}

@Composable
fun DetailBody(
    itemList: List<Route>,
    modifier: Modifier = Modifier,
    onItemClick: (Route) -> Unit
){
    LazyColumn(
        modifier = modifier
    ){
        items(
            items = itemList,
            key = { item -> item.favoriteCode}
        ) {item->
            RouteItem(
                item = item,
                onItemClick = {onItemClick(it)}
            )
        }
    }
}

@Composable
fun RouteItem(
    item: Route,
    onItemClick: (Route) -> Unit,
    modifier: Modifier = Modifier
){
    Row(modifier = modifier
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Column(
            modifier = modifier
                .clickable { onItemClick(item) }
                .padding(vertical = 5.dp)
        ) {
            Row(
                modifier = Modifier.width(300.dp)
            ) {
                Text(
                    text = item.departureCode,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = item.departureName,
                    modifier = Modifier.padding(start = 5.dp),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
            Row(
                modifier = Modifier.width(300.dp)
            ) {
                Text(
                    text = item.destinationCode,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = item.destinationName,
                    modifier = Modifier.padding(start = 5.dp),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
        }
        Spacer(Modifier.weight(1f))
        Icon(
            imageVector = if(item.userId!=null) Icons.Filled.Star else Icons.Outlined.StarBorder,
            tint = if(item.userId!=null) Color.Yellow else Color.Black,
            contentDescription = null
        )

    }

}

@Preview(showBackground = true)
@Composable
fun DetailPreview(){
    RouteItem(
        item =  Route(
            0, "ABC",
            "abcdefg", "bbb", "123456777777777777777777777"
        ),
        onItemClick = {}
    )
}