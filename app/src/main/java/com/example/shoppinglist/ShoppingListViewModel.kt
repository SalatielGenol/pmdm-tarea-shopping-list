package com.example.shoppinglist

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class ShoppingListViewModel: ViewModel(){
    private val _items = getShoppingListItems().toMutableStateList()
    val items: List<ShoppingDataItem>
        get() = _items

    fun close(item: ShoppingDataItem){
        _items.remove(element = item)
    }
}


private fun getShoppingListItems() = List(30) { i -> ShoppingDataItem(i, "Producto $i", false) }