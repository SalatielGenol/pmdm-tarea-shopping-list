package com.example.shoppinglist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ShoppingListScreen(viewModel: ShoppingListViewModel) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Shopping List") }) },
/*        floatingActionButton = {
            FloatingActionButton(
                onClick = { *//*TODO()*//* },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add Button")
            }
        }*/
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
        ) {
            items(items = viewModel.items, key = { ShoppingDataItem ->ShoppingDataItem.id }) {ShoppingDataItem ->
                ShoppingListItem(
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 10.dp)
                        .background(
                            MaterialTheme.colors.secondary,
                            shape = RoundedCornerShape(15.dp)
                        ),
                    itemName = ShoppingDataItem.name,
                    checkValue = ShoppingDataItem.checked,
                    onCheckedChange = { ShoppingDataItem.checked = !ShoppingDataItem.checked },
                    onItemClose = { viewModel.close(ShoppingDataItem) },
                )
            }
        }
    }
}