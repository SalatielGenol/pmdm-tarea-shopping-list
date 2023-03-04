package com.example.shoppinglist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ShoppingListItem(
    modifier: Modifier = Modifier,
    itemName: String,
    onItemClick: (() -> Unit)? = null,
    checkValue: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onItemClose: () -> Unit,
) {
    Row(
        modifier = modifier
            /*
            Dota al Row de la posibilidad de ser un elemento clickable con el único requisito
            de pasarle una lambda en el parámetro onItemClick. Si no se le pasa ningun argumento,
            dicha función queda deshabilitada y no permite recibir el evento onClick.
            */
            .clickable(
                enabled = onItemClick != null,
                onClick = onItemClick ?: {}
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Card() {

        }
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
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
    }
}