package ggv.ayush
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Student(
    val name: String,
    val rollNumber: Int,
    val presentState: MutableState<Boolean> = mutableStateOf(false)

)
