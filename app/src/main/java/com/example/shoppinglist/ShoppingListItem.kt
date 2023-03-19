package com.example.shoppinglist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun ShoppingListItem(
    itemName: String,
    checkValue: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onItemClose: () -> Unit,
    modifier: Modifier = Modifier,
    onItemClickEnable: Boolean = false,
    onItemClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .clickable(
                enabled = onItemClickEnable,
                onClick = onItemClick
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 20.dp)
                .padding(vertical = 20.dp),
            text = itemName
        )
        Checkbox(
            checked = checkValue,
            onCheckedChange = onCheckedChange
        )
        IconButton(onClick = onItemClose) {
            Icon(Icons.Filled.Close, contentDescription = stringResource(R.string.close))
        }
    }
}