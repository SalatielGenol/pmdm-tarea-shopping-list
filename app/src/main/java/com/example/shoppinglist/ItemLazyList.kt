package com.example.shoppinglist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemLazyList(
    paddingValues: PaddingValues,
    items: List<ShoppingDataItem>,
    listState: LazyListState,
/*    onCheckedChange: (ShoppingDataItem) -> Unit,*/
    onItemClose: (ShoppingDataItem) -> Unit
) {
    LazyColumn(
        state = listState,
        contentPadding = paddingValues,
        reverseLayout = true
    ) {
        items(
            items = items,
            key = { ShoppingDataItem -> ShoppingDataItem.id }
        ) { item ->
            ShoppingListItem(
                itemName = item.name,
/*                checkValue = item.checkValue,
                onCheckedChange = { onCheckedChange(item) },*/
                onItemClose = { onItemClose(item) },
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