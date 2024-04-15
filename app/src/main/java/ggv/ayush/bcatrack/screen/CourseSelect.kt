package ggv.ayush.bcatrack.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ggv.ayush.bcatrack.R

@Composable
fun CourseSelectionScreen(navController: NavController) {
    val backgroundColor = Color(0xFF09A9F1) // Light blue color
    val textColor = Color.White
    Box {
        ggv.ayush.bcatrack.screen.home.BackgroundImage(
            painter = painterResource(id = R.drawable.img_2),
            contentDescription = "Background Image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {
            Button(
                onClick = {
                    navController.popBackStack()
                    navController.navigate("main/BCA")
                },
                colors = ButtonDefaults.buttonColors(backgroundColor),
                modifier = Modifier
                    .padding(16.dp)
                    .width(300.dp)
                    .height(50.dp),
                shape = RectangleShape //
                ) {
                Text(text = "Take Attendance for BCA", color = textColor)
                 }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {  navController.popBackStack()
                    navController.navigate("main/BSC")

                },
                colors = ButtonDefaults.buttonColors(backgroundColor),
                modifier = Modifier
                    .padding(16.dp)
                    .width(300.dp)
                    .height(50.dp),
                shape = RectangleShape

            ) {
                Text(text = "Take Attendance for BSC CS ", color = textColor)
            }
        }

    }

}