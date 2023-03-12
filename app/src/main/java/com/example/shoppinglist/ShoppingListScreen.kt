package com.example.shoppinglist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShoppingListScreen() {
    val viewModel: ShoppingListViewModel = viewModel()
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Shopping List") }) },
        floatingActionButton = {
            if (viewModel.checkedFlag)
                FloatingManyClose { viewModel.closeManyItems() }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        Column(Modifier.padding(paddingValues)) {
            AddButtonConvertible(viewModel)
            /*BasicTextField(
                value = viewModel.inputItemText,
                onValueChange = { viewModel.inputItemTextValueChante(it) },
                singleLine = true,
                keyboardActions = KeyboardActions(
                    onDone = {
                        viewModel.newItem(
                            viewModel.inputItemText
                        )
                    },
                )
            )*/
            LazyColumn {
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
}

@Composable
fun FloatingManyClose(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Icon(Icons.Filled.Close, contentDescription = "Close")
    }
}

@Composable
fun AddButtonConvertible(viewModel: ShoppingListViewModel) {
    Box(
        modifier = Modifier
            .padding(
                horizontal = 35.dp,
                vertical = 5.dp
            ), contentAlignment = Alignment.Center
    ) {

        TextField(
            value = viewModel.inputItemText,
            onValueChange = { viewModel.inputItemTextValueChante(it) },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Something") },
            keyboardActions = KeyboardActions(
                onDone = {
                    viewModel.newItem(
                        viewModel.inputItemText
                    )
                },
            ),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
        )
        var visible by remember { mutableStateOf(true) }

        if (visible) {
            Button(
                onClick = { visible = false }, modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "Añadir elemento", modifier = Modifier.padding(5.dp))
            }
        }

    }
}