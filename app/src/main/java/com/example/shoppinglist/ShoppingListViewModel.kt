package com.example.shoppinglist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel

class ShoppingListViewModel : ViewModel() {
    private val _items = mutableStateListOf<ShoppingDataItem>()
    val items: List<ShoppingDataItem>
        get() = _items

    /*private val context =

    val dbHelper = SqlVanilla(context,"data.db", 1)*/

    private var itemID = 0

    private var isAddButtonVisible by mutableStateOf(true)

    fun getAddButtonState(): Boolean{
        return isAddButtonVisible
    }

    fun close(item: ShoppingDataItem) {
        _items.remove(element = item)
    }

    fun newItem(itemName: TextFieldValue) {
        _items.add(
            element = ShoppingDataItem(
                id = ++itemID,
                name = itemName.text,
                checked = false
            )
        )
    }

    fun showAddButton(){
        isAddButtonVisible = true
    }

    fun hideAddButton(){
        isAddButtonVisible = false
    }
}