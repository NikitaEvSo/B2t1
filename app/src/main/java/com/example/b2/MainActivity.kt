package com.example.b2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.b2.Screens.History
import com.example.b2.Screens.Home
import com.example.b2.Screens.Map
import com.example.b2.Screens.Moments
import com.example.b2.Screens.Recomendation
import com.example.b2.ui.theme.B2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var screen by remember { mutableStateOf(Home) }
            B2Theme {
                Navigation(screen, { it: Screens -> screen = it })
            }
        }
    }
}


@Composable
fun Navigation(screen: Screens, function: (Screens) -> Unit) {
    when (screen) {
        Home -> MainContent(function)
        History -> HistoryPage(function)
        Moments -> MomentsPage(function)
        Recomendation -> TODO()
        Map -> TODO()
    }
}

val backGroundColor = Color(92, 6, 100, 255)


@Composable
fun RotatingIcon(modifier: Modifier = Modifier) {
    Icon(
        painterResource(android.R.drawable.arrow_down_float), null, modifier = modifier.size(20.dp)
    )
}
