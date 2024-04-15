package ggv.ayush.bcatrack.screen.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ggv.ayush.bcatrack.R
import ggv.ayush.bcatrack.ui.theme.Purple500

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    logoPosition: Float,
) {
    val topBarContentColor = if (isSystemInDarkTheme()) Color.LightGray else Color.White
    val topBarBackgroundColor = if (isSystemInDarkTheme()) Color.Black else Purple500
    var logoResource by remember { mutableIntStateOf(R.drawable.img_1) }

    //for infinite rotation of the logo
    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )
    TopAppBar(
        title = {
            Text(
                text = "Attendence Tracker",
                color = topBarContentColor
            )
        },
        backgroundColor = topBarBackgroundColor,
        actions = {
            IconButton(onClick = {
                logoResource =
                    if (logoResource == R.drawable.img_1) R.drawable.img else R.drawable.img_1
            }
            ) {
                Icon(
                    painter = painterResource(id = logoResource),
                    contentDescription = "",
                    modifier = Modifier
                        .size(70.dp)
                        .padding(2.dp)
                        .rotate(angle)
                        .offset(y = Dp(logoPosition)),

                    tint = if (isSystemInDarkTheme()) Color.White else Color.White
                )

            }
        }
    )
}