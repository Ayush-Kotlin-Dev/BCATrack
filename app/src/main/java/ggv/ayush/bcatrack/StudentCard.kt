package ggv.ayush.bcatrack

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Colors
import ggv.ayush.Student


@Composable
fun StudentCard(student: Student, onPresentClicked: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(top = 46.dp)
            .fillMaxWidth()
            .height(500.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Profile Image
            Image(
                imageVector = Icons.Rounded.Person,
                contentDescription = "Profile Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Student Name
            Text(
                text = student.name,
                style = TextStyle(fontSize = 20.sp),
                color = if(student.presentState.value) {
                    androidx.compose.ui.graphics.Color.Green
                } else {
                    androidx.compose.ui.graphics.Color.Red
                },
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Roll Number
            Text(
                text = "Roll No: ${student.rollNumber}",
                style = TextStyle(fontSize = 16.sp),
                textAlign = TextAlign.Center,
                color = if(isSystemInDarkTheme()) {
                    androidx.compose.ui.graphics.Color.White
                } else {
                    androidx.compose.ui.graphics.Color.Black
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(66.dp))

            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Checkbox(
                    checked = student.presentState.value,
                    onCheckedChange = { student.presentState.value = it },
                    modifier = Modifier.padding(end = 8.dp)
                )
                val isDarkTheme = isSystemInDarkTheme()
                val backgroundColor = if (isDarkTheme) Color.Black else Color.White
                val textColor = if (isDarkTheme) Color.White else Color.Black

                Button(
                    onClick = onPresentClicked,
                    colors = ButtonDefaults.buttonColors(backgroundColor),
                    modifier = Modifier.align(Alignment.CenterVertically).clip(RoundedCornerShape(0))
                ) {
                    Text(text = "Present", color = textColor)
                }
            }

        }
    }
}
