package com.example.shoppinglist

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShoppingListScreen() {
    val viewModel: ShoppingListViewModel = viewModel()
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Shopping List") }) },
        floatingActionButton = {
            if (viewModel.checkedFlag)
                FloatingManyClose { viewModel.closeManyItems() }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        Column(Modifier.padding(paddingValues)) {
            AddButtonConvertible(
                viewModel = viewModel,
                kbOnDone = {
                    if (viewModel.inputItemText.isNotBlank()) {
                        viewModel.newItem(viewModel.inputItemText)
                        viewModel.inputItemTextValueSet("")
                        coroutineScope.launch { listState.scrollToItem(index = viewModel.items.last().id) }
                    }
                }
            )
            LazyColumn(
                state = listState,
                contentPadding = paddingValues
            ){
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
fun AddButtonConvertible(
    viewModel: ShoppingListViewModel,
    kbOnDone: (KeyboardActionScope) -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(
                horizontal = 12.dp,
                vertical = 5.dp
            ), contentAlignment = Alignment.Center
    ) {
        var hasFocus by remember { mutableStateOf(false) }
        val focusRequester = remember { FocusRequester() }
        val focusManager = LocalFocusManager.current

        BackHandler(hasFocus) {
            focusManager.clearFocus()
        }

        TextField(
            value = viewModel.inputItemText,
            onValueChange = { viewModel.inputItemTextValueSet(it) },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                    hasFocus = focusState.hasFocus
                },
            placeholder = { Text(text = "Añadir producto") },
            keyboardActions = KeyboardActions(
                onDone =  kbOnDone
            ),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        if (!hasFocus) {
            Button(
                onClick = {
                    hasFocus = !hasFocus
                    focusRequester.requestFocus()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Añadir producto", modifier = Modifier.padding(5.dp))
            }
        }

    }
}