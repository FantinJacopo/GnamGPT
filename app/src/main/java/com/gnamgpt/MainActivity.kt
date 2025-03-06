package com.gnamgpt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.gnamgpt.ui.navigation.App
import com.gnamgpt.ui.theme.GnamGPTTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GnamGPTTheme {
                Surface {
                    App()
                }
            }
        }
    }
}