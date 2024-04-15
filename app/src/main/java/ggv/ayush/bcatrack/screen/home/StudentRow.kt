package ggv.ayush.bcatrack.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ggv.ayush.bcatrack.Student

@Composable
fun StudentList(students: List<Student>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(600.dp)
            .padding(top = 30.dp)
    ) {
        items(students) { student ->
            StudentRow(student)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun StudentRow(student: Student) {
    val backgroundColor = when (student.clickState.value) {
        0 -> Color.White
        1 -> Color.Green
        else -> Color.Red
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                student.clickState.value = (student.clickState.value + 1) % 3
                student.presentState.value = student.clickState.value == 1
            }
            .background(backgroundColor)
            .padding(16.dp)
            ,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = student.rollNumber.toString())
        Text(text = student.name)
        Checkbox(checked = student.presentState.value, onCheckedChange = null)
    }

}