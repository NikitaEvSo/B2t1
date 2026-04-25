package com.example.b2

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainContent(function: (Screens) -> Unit) {
    var isLoaded by remember { mutableStateOf(false) }
    val imageBitmap = ImageBitmap .imageResource(id = R.drawable.devia114)
    val rectColor = Color .Magenta

    val rectSize = Size(200f, 50f)
    val mainScreenTextStyle = TextStyle(
        fontSize = 200.sp, fontWeight = FontWeight .Black, color = Color .White
    )
//+==+==+==+==+==+==+==+Spec+==+==+==+==+==+==+==+==+==
    val animationSpec = tween<Float>(
        durationMillis = 800, easing = EaseIn
    )
    val rectAnimationSpec = tween<Float>(
        delayMillis = 100, durationMillis = 900, easing = EaseIn
    )
//+==+==+==+==+==+==+==+X Offset+==+==+==+==+==+==+==+==+==
    val animX by animateFloatAsState(
        targetValue = if (isLoaded) 0f else 200f, animationSpec = animationSpec, label = "offsetX"
    )
    val rectAnimX by animateFloatAsState(
        targetValue = if (isLoaded) 100f else 1200f,
        animationSpec = rectAnimationSpec,
        label = "offsetX"
    )
//+==+==+==+==+==+==+==+Y Offset+==+==+==+==+==+==+==+==+==
    val animY by animateFloatAsState(
        targetValue = if (isLoaded) 0f else -500f, animationSpec = animationSpec, label = "offsetY"
    )

    val rectAnimY by animateFloatAsState(
        targetValue = if (isLoaded) 100f else -800f,
        animationSpec = rectAnimationSpec,
        label = "offsetY"
    )
//+==+==+==+==+==+==+==+Rotation+==+==+==+==+==+==+==+==+==
    val animRotation by animateFloatAsState(
        targetValue = if (isLoaded) 0f else 180f, animationSpec = animationSpec, label = "rotationZ"
    )

    val textMod = Modifier 
        .graphicsLayer(translationX = animX, translationY = animY)
        .clickable { isLoaded = !isLoaded }

    Column(
        horizontalAlignment = Alignment .CenterHorizontally,
        modifier = Modifier 
            .fillMaxSize()
            .background(backGroundColor)
            .padding(60.dp),

        ) {
        RotatingIcon(
            modifier = Modifier 
                .graphicsLayer(rotationZ = animRotation)
                .clickable { isLoaded = !isLoaded })
    }

    Box(
        Modifier 
            .fillMaxSize().clickable(onClick = { function(Screens.History) })
            .graphicsLayer(translationX = animX, translationY = animY)
    ) {
        Canvas(
            modifier = Modifier 
                .fillMaxSize()
                .graphicsLayer(
                    clip = false,
                    translationY = rectAnimY,
                    translationX = rectAnimX,
                    renderEffect = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        RenderEffect.createBlurEffect(
                            30f, 30f, Shader.TileMode.DECAL
                        ).asComposeRenderEffect()
                    } else null
                ), onDraw = {

                rotate(degrees = -45f) {
                    drawRect(
                        color = rectColor, topLeft = Offset(-300f, 200f), size = rectSize
                    )
                    drawRect(
                        color = rectColor, topLeft = Offset(300f, 500f), size = rectSize
                    )
                    drawRect(
                        color = rectColor, topLeft = Offset(150f, 850f), size = rectSize
                    )
                    drawRect(
                        color = rectColor, topLeft = Offset(700f, 900f), size = rectSize
                    )
                    drawRect(
                        color = rectColor, topLeft = Offset(300f, 700f), size = rectSize
                    )
                }

            })

        Row(modifier = textMod.align(alignment = Alignment .Center)) {
            Text(
                "L", style = mainScreenTextStyle, modifier = Modifier .offset(y = (-20).dp)
            )
            Text(
                "Y", style = mainScreenTextStyle, modifier = Modifier .offset(y = 20.dp)
            )
            FilledText(
                imageBitmap, Modifier 
            )
            Text(
                "N", style = mainScreenTextStyle, modifier = Modifier .offset(y = 20.dp)
            )
        }

        Canvas(
            modifier = Modifier 
                .size(300.dp)
                .graphicsLayer(translationY = rectAnimY, translationX = rectAnimX), {

                rotate(degrees = -45f) {
                    drawRect(
                        color = rectColor, topLeft = Offset(-200f, 400f), size = rectSize
                    )
                    drawRect(
                        color = rectColor, topLeft = Offset(400f, 700f), size = rectSize
                    )
                    drawRect(
                        color = rectColor, topLeft = Offset(150f, 1150f), size = rectSize
                    )
                    drawRect(
                        color = rectColor, topLeft = Offset(700f, 2000f), size = rectSize
                    )
                    drawRect(
                        color = rectColor, topLeft = Offset(200f, 900f), size = rectSize
                    )
                }

            })

    }
}

@Composable
private fun FilledText(imageBitmap: ImageBitmap, modifier: Modifier = Modifier ) {
    val brushC = remember(imageBitmap) {
        ShaderBrush(
            ImageShader(
                imageBitmap, TileMode .Mirror, TileMode .Mirror
            )
        )
    }
    Text(
        text = "O", style = TextStyle(
            brush = brushC, fontSize = 200.sp, fontWeight = FontWeight .Black
        ), modifier = modifier
    )
}