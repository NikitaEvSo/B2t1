package com.example.b2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@Composable
fun MomentsPage(function: (Screens) -> Unit) {
    Row(Modifier.Companion.fillMaxSize().background(backGroundColor)) {
        Column(Modifier.Companion.weight(2f).padding(200.dp)) {
            Text(buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Companion.White)) {
                    appendLine("Unforgettable\n")
                    append("Moments in ")
                }
                withStyle(style = SpanStyle(color = Color.Companion.Magenta)) {
                    append("Lyon")
                }
            }

            )
            Spacer(Modifier.Companion.height(30.dp))
            Text(
                color = Color.Companion.LightGray,
                text = "Lyon, the third-largest city in France, is a charming destination that offers a rich blend of history, culture, and culinary delights. Nestled in the heart of the Rhône-Alpes region, this vibrant city boasts a stunning landscape, with the Rhône and Saône rivers meandering through its streets. As you explore Lyon, you’ll uncover an array of unforgettable moments that will leave a lasting impression on your heart."
            )

            Row() {
                Column() {
                    Text("5.5M+", color = Color.Companion.White)
                    Text("Visitors", color = Color.Companion.LightGray)
                }
                Column() {
                    Text("10.2M+", color = Color.Companion.White)
                    Text("Photography", color = Color.Companion.LightGray)
                }
            }
            Button(
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Companion.White, containerColor = Color.Companion.Magenta
                ), onClick = { function(Screens.Recomendation) }) {
                Text("Explore", color = Color.Companion.White)
            }
        }
        val lenght = Int.MAX_VALUE
        val lazystate1 = rememberLazyListState()
        val lazystate2 = rememberLazyListState()
        LaunchedEffect(Unit) {
            while (true) {
                withFrameNanos { }
                lazystate1.scrollBy(2f)

            }
        }
        LaunchedEffect(Unit) {
            while (true) {
                withFrameNanos { }
                lazystate2.scrollBy(4f)

            }
        }

        Row(modifier = Modifier.Companion.weight(1f)) {
            LazyColumn(state = lazystate1) {
                items(lenght) {
                    Image(
                        painter = painterResource(R.drawable.devia114),
                        contentDescription = null,
                        Modifier.Companion.width(200.dp)
                    )
                    Spacer(Modifier.Companion.height(2.dp))

                }
            }
            Spacer(Modifier.Companion.width(3.dp))
            LazyColumn(state = lazystate2) {
                items(lenght) {
                    Image(
                        painter = painterResource(R.drawable.devia114),
                        contentDescription = null,
                        Modifier.Companion.width(200.dp)
                    )
                    Spacer(Modifier.Companion.height(2.dp))
                }
            }
        }
    }
}