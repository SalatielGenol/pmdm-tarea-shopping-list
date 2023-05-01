package com.example.shoppinglist

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel

class ShoppingListViewModel(context: Context) : ViewModel() {
    private val dbHelper = SqlVanilla(context,"data.db", 1)
    private val cursorDB = dbHelper.getData()

    private val _items = mutableStateListOf<ShoppingDataItem>().apply {
        if (cursorDB.moveToFirst()) {
            do {
                this.add(
                    element = ShoppingDataItem(
                        id = cursorDB.getInt(0),
                        name = cursorDB.getString(1),
                        checked = cursorDB.getInt(2) > 0
                    )
                )
            } while (cursorDB.moveToNext())
        }
    }
    val items: List<ShoppingDataItem>
        get() = _items

    /*private var itemID = 0*/
    private var isAddButtonVisible by mutableStateOf(true)

    fun getAddButtonState(): Boolean{
        return isAddButtonVisible
    }

    fun close(item: ShoppingDataItem) {
        dbHelper.removeItem(id = item.id)
        _items.remove(element = item)
    }

    fun newItem(itemName: TextFieldValue) {
        dbHelper.addItem(name = itemName.text, checked = false)
        val cursorDB = dbHelper.getData()
        if (cursorDB.moveToFirst()){
            cursorDB.moveToLast()
            _items.add(
                element = ShoppingDataItem(
                    id = cursorDB.getInt(0),
                    name = cursorDB.getString(1),
                    checked = cursorDB.getInt(2) > 0
                )
            )
        }
    }

    fun showAddButton(){
        isAddButtonVisible = true
    }

    fun hideAddButton(){
        isAddButtonVisible = false
    }
}