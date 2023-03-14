package com.example.shoppinglist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ShoppingListViewModel : ViewModel() {
    private val _items = mutableStateListOf<ShoppingDataItem>()/*getShoppingListItems().toMutableStateList()*/
    val items: List<ShoppingDataItem>
        get() = _items.asReversed()

    var checkedFlag by mutableStateOf(false)
        private set

    var inputItemText: String by mutableStateOf("")
        private set

    fun close(item: ShoppingDataItem) {
        _items.remove(element = item)
    }

    fun onChangeCheck(item: ShoppingDataItem) {
        item.checkValue = !item.checkValue
        checkedFlag = items.any { ShoppingDataItem -> ShoppingDataItem.checkValue }
    }

    fun closeManyItems() {
        _items.removeIf { ShoppingDataItem -> ShoppingDataItem.checkValue }
        checkedFlag = items.any { ShoppingDataItem -> ShoppingDataItem.checkValue }
    }

    fun inputItemTextValueSet(text: String) {
        inputItemText = text
    }

    fun newItem(itemName: String) {
        _items.add(
            element = ShoppingDataItem(
                id = _items.size + 1,
                name = itemName,
                checked = false
            )
        )
    }
}


/*
private fun getShoppingListItems() =
    List(30) { i -> ShoppingDataItem(id = i, name = "Producto $i", checked = false) }*/
