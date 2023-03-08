package com.example.shoppinglist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class ShoppingListViewModel: ViewModel(){
    private val _items = getShoppingListItems().toMutableStateList()
    var checkedFlag by mutableStateOf(false)
    val items: List<ShoppingDataItem>
        get() = _items

    fun close(item: ShoppingDataItem){
        _items.remove(element = item)
    }

    fun onChangeCheck(item: ShoppingDataItem){
        item.checkValue = !item.checkValue
        checkedFlag = items.any { ShoppingDataItem -> ShoppingDataItem.checkValue }
    }

    fun closeManyItems(){
        _items.removeIf { ShoppingDataItem -> ShoppingDataItem.checkValue }
        checkedFlag = items.any { ShoppingDataItem -> ShoppingDataItem.checkValue }
    }
}


private fun getShoppingListItems() = List(30) { i -> ShoppingDataItem(id = i, name = "Producto $i", checked = false) }