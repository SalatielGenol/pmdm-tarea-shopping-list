package com.example.shoppinglist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShoppingListScreen(viewModel: ShoppingListViewModel) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Shopping List") }) },
        floatingActionButton = { if (viewModel.checkedFlag)
            FloatingManyClose { viewModel.closeManyItems() }
        } ,
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
        ) {
            items(
                items = viewModel.items,
                key = { item -> item.id }
            ) { item ->
                ShoppingListItem(
                    modifier = Modifier
                        .padding(
                            horizontal = 10.dp,
                            vertical = 10.dp
                        )
                        .background(
                            MaterialTheme.colors.secondary,
                            shape = RoundedCornerShape(15.dp)
                        )
                        .animateItemPlacement(),
                    itemName = item.name,
                    checkValue = item.checkValue,
                    onCheckedChange = { viewModel.onChangeCheck(item) },
                    onItemClose = { viewModel.close(item) },
                )
            }
        }
    }
}

@Composable
fun FloatingManyClose(onClick: () -> Unit){
    FloatingActionButton(
        onClick = onClick,
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Icon(Icons.Filled.Close, contentDescription = "Close")
    }
}