package com.example.shoppinglist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ShoppingDataItem(val id: Int, val name: String, var checked: Boolean){
    var checkValue by mutableStateOf(checked)
}