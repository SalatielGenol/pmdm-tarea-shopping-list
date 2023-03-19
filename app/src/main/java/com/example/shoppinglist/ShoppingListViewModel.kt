package com.example.shoppinglist

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ShoppingListViewModel : ViewModel() {
    private val _items = mutableStateListOf<ShoppingDataItem>()
    val items: List<ShoppingDataItem>
        get() = _items

    private var checkedFlag = mutableStateOf(false)

    private var itemID = 0

    fun close(item: ShoppingDataItem) {
        _items.remove(element = item)
    }

    fun onChangeCheck(item: ShoppingDataItem) {
        item.checkValue = !item.checkValue
        checkedFlag.value = items.any { ShoppingDataItem -> ShoppingDataItem.checkValue }
    }

    fun closeManyItems() {
        _items.removeIf { ShoppingDataItem -> ShoppingDataItem.checkValue }
        checkedFlag.value = items.any { ShoppingDataItem -> ShoppingDataItem.checkValue }
    }

    fun newItem(itemName: String) {
        _items.add(
            element = ShoppingDataItem(
                id = ++itemID,
                name = itemName,
                checked = false
            )
        )
    }

    fun areItemsChecked(): Boolean {
        return checkedFlag.value
    }
}