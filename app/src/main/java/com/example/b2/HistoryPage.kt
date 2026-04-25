package com.example.b2

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

val backGroundColor2 = Color(28, 4, 94, 255)

data class Moment(
    val title: String, val subtitle: String, val description: String, val imageRes: Int
)

@Composable
fun HistoryPage(function: (Screens) -> Unit) {

    Column(Modifier.Companion.fillMaxSize().background(color = backGroundColor2).padding(30.dp)) {
        Text("History of Lyon")
        val momentsData = remember {
            listOf(
                Moment(
                    "1995", "Основание", "Начало долгого пути в разработке.", R.drawable.devia114
                ),
                Moment("2005", "Прорыв", "Выход на международный рынок.", R.drawable.devia114),
                Moment(
                    "2015",
                    "Эра Mobile",
                    "Переход на Kotlin и современные стеки.",
                    R.drawable.devia114
                ),
                Moment(
                    "2024",
                    "AI Интеграция",
                    "Внедрение нейросетей в экосистему.",
                    R.drawable.devia114
                ),
                Moment(
                    "2026", "Будущее", "Создание интерфейсов нового поколения.", R.drawable.devia114
                )
            )
        }
        val progressStates = remember { momentsData.map { mutableFloatStateOf(0f) } }
        var currentStep by remember { mutableIntStateOf(0) }
        LaunchedEffect(Unit) {
            progressStates.forEachIndexed { index, state ->
                currentStep = index
                Animatable(state.floatValue).animateTo(
                    targetValue = 1f,
                    animationSpec = tween(durationMillis = 5000, easing = LinearEasing)
                ) {
                    state.floatValue = value
                }
            }
            function(Screens.Moments)
        }
        Row() {
            Text("Lion is the ancient", color = Color.Companion.White)
            Text("capital of Gaul", color = Color.Companion.Magenta)
        }
        Row() {
            Text("With a rich history of ", color = Color.Companion.White)
            Text("more than 2000 years.", color = Color.Companion.Magenta)
        }
        Row(Modifier.Companion.padding(10.dp)) {
            val ttile = listOf<String>(
                "Roman Lyon",
                "Roman Lyon",
                "Roman Lyon",
                "Roman Lyon",
                "Roman Lyon"
            )
            Row(
                modifier = Modifier.Companion
                    .padding(10.dp)
                    .fillMaxWidth().height(40.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                progressStates.forEachIndexed { index, progress ->
                    ProgressBarWithTitle(
                        progress = progress.floatValue,
                        text = ttile[index],
                        modifier = Modifier.Companion.weight(1f)
                    )
                }
            }
        }
        val currentMoment = momentsData[currentStep]

        HistoryCard(
            image = painterResource(R.drawable.devia114),
            title = currentMoment.subtitle,
            textMain = currentMoment.description,
            text = currentMoment.title
        )
    }

}


@Composable
fun HistoryCard(
    image: Painter = painterResource(R.drawable.devia114),
    title: String,
    textMain: String,
    text: String
) {
    Row(
        Modifier.Companion
            .fillMaxSize()
            .padding(vertical = 20.dp)
    ) {
        Image(painter = image, contentDescription = null)
        Column(
            Modifier.Companion
                .fillMaxWidth()
                .padding(start = 20.dp, top = 10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.Companion.fillMaxWidth()
            ) {
                Text(title, color = Color.Companion.Magenta)
                Text(text, color = Color.Companion.White)
            }
            Text(textMain, color = Color.Companion.White)
        }
    }

}

@Composable
fun ProgressBarWithTitle(modifier: Modifier = Modifier.Companion, text: String, progress: Float) {
    Column(horizontalAlignment = Alignment.Companion.CenterHorizontally) {
        LinearProgressIndicator(
            progress = { progress },
            modifier = modifier.height(4.dp),
            color = Color.Companion.White,
            trackColor = Color(252, 252, 252, 96),
            strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
        )
        Text(text, color = Color.Companion.White)
    }

}