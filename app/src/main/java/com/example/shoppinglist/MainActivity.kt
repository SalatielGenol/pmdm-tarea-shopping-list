package com.example.shoppinglist

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.shoppinglist.ui.theme.ShoppingListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_UNCHANGED)
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingListTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ShoppingListScreen()
                }
            }
        }
    }
}

// https://stackoverflow.com/questions/74369518/how-to-move-cursor-to-the-end-of-the-textfield-after-screen-rotation-or-system-t
// https://www.composables.com/tutorials/focus-text
// https://medium.com/google-developer-experts/focus-in-jetpack-compose-6584252257fe