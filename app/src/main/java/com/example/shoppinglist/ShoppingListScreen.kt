package com.example.shoppinglist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ShoppingListScreen() {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Shopping List") }) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO()*/ },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add Button")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            //verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            items(count = 25) { productID ->
                var checked by rememberSaveable { mutableStateOf(false) }
                ShoppingListItem(
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 10.dp)
                        .background(
                            MaterialTheme.colors.secondary,
                            shape = RoundedCornerShape(15.dp)
                        ),
                    onItemClick = {},
                    itemName = "Producto $productID",
                    checkValue = checked,
                    onCheckedChange = { checked = !checked },
                    onItemClose = { /* TODO() */ },
                )
            }
        }
    }
}