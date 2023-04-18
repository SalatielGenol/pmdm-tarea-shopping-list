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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShoppingListScreen() {
    val viewModel: ShoppingListViewModel = viewModel()
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val addItemFocusRequest = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = stringResource(R.string.scaffold_name),
                )
            })
        },
        floatingActionButton = {
            if (viewModel.areItemsChecked())
                FloatingButtonFewItemsClose { viewModel.closeManyItems() }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        Column(Modifier.padding(paddingValues)) {
            Box(
                modifier = Modifier
                    .padding(
                        horizontal = 12.dp,
                        vertical = 7.dp
                    ), contentAlignment = Alignment.Center
            ){
                if(!viewModel.isVisible){
                    AddItemButton(
                        text = stringResource(R.string.add_product),
                        onClick = {
                            viewModel.isVisible = it
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }else{
                    LaunchedEffect(Unit){
                        delay(500)
                        addItemFocusRequest.requestFocus()
                    }
                    BackHandler() {
                        focusManager.clearFocus()
                        viewModel.isVisible = false
                        viewModel.inputItemText = TextFieldValue("")
                    }
                    AddItemTextField(
                        inputItemText = viewModel.inputItemText,
                        onValueChange = { viewModel.inputItemText = it },
                        keyBoardOnDone = {
                            if (viewModel.inputItemText.text.isNotBlank()) {
                                viewModel.newItem(viewModel.inputItemText.text)
                                viewModel.inputItemText = TextFieldValue("")
                                coroutineScope.launch {
                                    listState.animateScrollToItem(index = viewModel.items.size)
                                }
                            }
                        },
                        focusRequester = addItemFocusRequest
                    )
                }
            }
            LazyColumn(
                state = listState,
                contentPadding = paddingValues,
                reverseLayout = true
            ) {
                items(
                    items = viewModel.items,
                    key = { ShoppingDataItem -> ShoppingDataItem.id }
                ) { item ->
                    ShoppingListItem(
                        itemName = item.name,
                        checkValue = item.checkValue,
                        onCheckedChange = { viewModel.onChangeCheck(item) },
                        onItemClose = { viewModel.close(item) },
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
                    )
                }
            }
        }
    }
}

@Composable
fun FloatingButtonFewItemsClose(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Icon(Icons.Filled.Close, contentDescription = stringResource(R.string.close))
    }
}

@Composable
fun AddItemTextField(
    inputItemText: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    keyBoardOnDone: (KeyboardActionScope) -> Unit,
    focusRequester: FocusRequester
) {
    TextField(
        value = inputItemText,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        placeholder = { Text(text = stringResource(R.string.add_product)) },
        keyboardActions = KeyboardActions(
            onDone = keyBoardOnDone
        ),
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun AddItemButton(
    text: String,
    onClick: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = {
            onClick(true)
        },
        modifier = modifier
    ) {
        Text(text = text, modifier = Modifier.padding(5.dp))
    }
}