package com.example.shoppinglist

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ShoppingListItem(
    itemName: String,
    checkValue: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onItemDelete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.then(
            Modifier
                .fillMaxWidth()
                .padding(all = 10.dp)
        ),
        shape = RoundedCornerShape(size = 10.dp),
        backgroundColor = MaterialTheme.colors.secondary
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 20.dp)
                    .padding(vertical = 20.dp), text = itemName
            )
            Checkbox(
                checked = checkValue,
                onCheckedChange = onCheckedChange
            )
            IconButton(onClick = onItemDelete) {
                Icon(Icons.Filled.Close, contentDescription = "Close")
            }
        }

    }
}