package ggv.ayush.bcatrack
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Student(
    val name: String,
    val rollNumber: Int,
    val presentState: MutableState<Boolean> = mutableStateOf(false),
    val clickState: MutableState<Int> = mutableStateOf(0) // Add this line to add the clickState property

)
