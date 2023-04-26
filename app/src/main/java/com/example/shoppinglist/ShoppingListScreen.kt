package com.example.shoppinglist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ShoppingListScreen() {
    val viewModel: ShoppingListViewModel = viewModel()
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val addItemFocusRequest = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    var inputItemText by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = stringResource(R.string.scaffold_name))
            })
        },
    ) { paddingValues ->
        Column(Modifier.padding(paddingValues)) {
            Box(
                modifier = Modifier
                    .padding(
                        horizontal = 12.dp,
                        vertical = 7.dp
                    ), contentAlignment = Alignment.Center
            ) {
                if (viewModel.getAddButtonState()) {
                    AddItemButton(
                        text = stringResource(R.string.add_product),
                        onClick = {
                            viewModel.hideAddButton()
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                } else {
                    LaunchedEffect(Unit) {
                        delay(500)
                        addItemFocusRequest.requestFocus()
                    }
                    AddItemTextField(
                        inputValue = inputItemText,
                        onValueChange = { inputItemText = it },
                        focusRequester = addItemFocusRequest,
                        keyBoardOnDone = {
                            if (inputItemText.text.isNotBlank()) {
                                viewModel.newItem(inputItemText)
                                inputItemText = TextFieldValue("")
                                coroutineScope.launch {
                                    listState.animateScrollToItem(index = viewModel.items.size)
                                }
                            }
                        },
                        onBack = {
                            inputItemText = TextFieldValue("")
                            focusManager.clearFocus()
                            viewModel.showAddButton()
                        }
                    )
                }
            }
            ItemLazyList(
                paddingValues = paddingValues,
                items = viewModel.items,
                listState = listState,
/*                onCheckedChange = {},*/
                onItemClose = { viewModel.close(it) }
            )
        }
    }
}