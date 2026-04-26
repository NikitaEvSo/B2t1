package com.example.b2

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSliderState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.b2.Screens.History
import com.example.b2.Screens.Home
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
        Recomendation -> RecomendationPage(function)
    }
}

val backGroundColor = Color(92, 6, 100, 255)
val animationSpec = tween<Float>(
    delayMillis = 0, durationMillis = 900, easing = EaseIn
)
val animationSpecDp = tween<Dp>(
    delayMillis = 0, durationMillis = 900, easing = EaseIn
)

@Composable
fun RowScope.HideShowArea(modifier: Modifier = Modifier) {
    Column(modifier.weight(1f)) {
        TtdBlock()
        BudgetBlock()
        TrBlock()
    }
}

@Composable
private fun TtdBlock() {
    var isOpened1 by remember { mutableStateOf(true) }
    val height by animateDpAsState(
        targetValue = if (isOpened1) 0.dp else 150.dp,
        animationSpec = animationSpecDp,
        label = "offsetX"
    )
    Column() {
        Row {
            Text("Things to do")
            val animRotation by animateFloatAsState(
                targetValue = if (isOpened1) 0f else 180f,
                animationSpec = animationSpec,
                label = "rotationZ"
            )
            RotatingIcon(
                modifier = Modifier
                    .graphicsLayer(rotationZ = animRotation)
                    .clickable(onClick = { isOpened1 = !isOpened1 })
            )
        }
        FlowRow(modifier = Modifier.height(height).clipToBounds()) {
            tag("texttexttext")
            tag("texttexttext")
            tag("texttexttext")
            tag("texttexttext")
        }
    }
}

@Composable
private fun TrBlock() {
    var isOpened1 by remember { mutableStateOf(true) }
    val height by animateDpAsState(
        targetValue = if (isOpened1) 0.dp else 240.dp,
        animationSpec = animationSpecDp,
        label = "offsetX"
    )
    Column() {
        Row {
            Text("TravelerRating")
            val animRotation by animateFloatAsState(
                targetValue = if (isOpened1) 0f else 180f,
                animationSpec = animationSpec,
                label = "rotationZ"
            )
            RotatingIcon(
                modifier = Modifier
                    .graphicsLayer(rotationZ = animRotation)
                    .clickable(onClick = { isOpened1 = !isOpened1 })
            )
        }
        Column() {
            Column() {
                val checkStates = remember {
                    List(5) {
                        mutableStateOf(false)
                    }
                }
                Column(modifier = Modifier.height(height).clipToBounds()) {
                    checkStates.forEachIndexed { index, state ->

                        Row() {
                            Checkbox(
                                checked = state.value,
                                onCheckedChange = { state.value = !state.value },
                            )
                            Text("🌟".repeat(5 - index) + "⭐".repeat(index))
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BudgetBlock() {
    var isOpened1 by remember { mutableStateOf(true) }
    val height by animateDpAsState(
        targetValue = if (isOpened1) 0.dp else 150.dp,
        animationSpec = animationSpecDp,
        label = "offsetX"
    )
    Column() {
        Row {
            Text("Things to do")
            val animRotation by animateFloatAsState(
                targetValue = if (isOpened1) 0f else 180f,
                animationSpec = animationSpec,
                label = "rotationZ"
            )
            RotatingIcon(
                modifier = Modifier
                    .graphicsLayer(rotationZ = animRotation)
                    .clickable(onClick = { isOpened1 = !isOpened1 })
            )
        }
        Column(modifier = Modifier.height(height).clipToBounds()) {
            val state = rememberSliderState(
                10000f, valueRange = 0f..100000f
            )

            Row() {
                Text("$ ${state.value}")
                Text("$ 800 000")
            }
            Slider(state, modifier = Modifier)
        }
    }
}

@Composable
fun tag(text: String, onClick: () -> Unit = {}) {
    Box(Modifier.border(1.dp, Color.Gray, RoundedCornerShape(45))) {
        Text(text)
    }
}

@Composable
fun RecomendationPage(function: (Screens) -> Unit) {
    var expand by remember { mutableStateOf(false) }
    val animExpand by animateFloatAsState(
        targetValue = if (expand) 100000f else 3f
    )
    Row(modifier = Modifier.padding(15.dp)) {
        HideShowArea()
        Column(modifier = Modifier.weight(animExpand)) {
            Box(
                Modifier
                    .weight(animExpand)
                    .fillMaxSize()
                    .background(Color.Gray)
                    .padding(25.dp)
                    .clickable(onClick = { expand = !expand })
            ) {
                Icon(painterResource(R.drawable.ic_menu_zoom), contentDescription = null)

            }
            Row(Modifier.weight(5f)) {
                Column(Modifier.weight(2f)) {
                    Text("Recommended to you")
                    LazyVerticalGrid(
                        modifier = Modifier.padding(10.dp), columns = GridCells.Fixed(2)
                    ) {
                        items(20) {
                            Box() {
                                Image(
                                    modifier = Modifier.fillMaxSize(),
                                    painter = painterResource(com.example.b2.R.drawable.devia114),
                                    contentDescription = null
                                )
                                Column(
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .align(Alignment.BottomStart)
                                ) {
                                    Text("Title")
                                    Text("Description")
                                }
                            }
                        }
                    }

                }
                Column() {
                    Text("Comments")
                    LazyColumn {
                        items(3) {
                            Column() {
                                Text("TitleTitleTitleTitleTitleTitle")
                                Row() {
                                    Box(
                                        modifier = Modifier
                                            .size(24.dp)
                                            .clip(shape = CircleShape)
                                            .background(Color.Magenta)

                                    )
                                    Text("User123")
                                }
                                Text("TextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextText")
                                Text("Show Less")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RotatingIcon(modifier: Modifier = Modifier) {
    Icon(
        painterResource(R.drawable.arrow_down_float), null, modifier = modifier.size(20.dp)
    )
}
