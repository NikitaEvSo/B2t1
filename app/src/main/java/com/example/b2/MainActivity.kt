package com.example.b2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.b2.ui.theme.B2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            B2Theme {
                val imageBitmap = ImageBitmap.imageResource(id = R.drawable.devia114)
                var brushC= remember(imageBitmap){
                    ShaderBrush(ImageShader(imageBitmap,
                        TileMode.Mirror, TileMode.Mirror))}
                    Text(
                        text = "G",
                        style = TextStyle(
                            brush = brushC, // Применяем картинку как кисть
                            fontSize = 200.sp,
                            fontWeight = FontWeight.Black
                        )
                    )

                }
            }
        }
}