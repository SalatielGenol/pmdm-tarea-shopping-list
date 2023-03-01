package com.example.shoppinglist

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ShoppingListScreen() {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Shopping List") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }, backgroundColor = MaterialTheme.colors.primary) {
                Icon(Icons.Filled.Add, contentDescription = "Add Button")
            }
        }
    ) {
        LazyColumn(Modifier.padding(it)) {
            items(count = 10) {
                ShoppingListItem(itemName = "Producto $it", checked = false)
            }
        }
    }
}