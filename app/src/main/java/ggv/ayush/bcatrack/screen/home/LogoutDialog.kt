package ggv.ayush.bcatrack.screen.home

import android.app.Dialog
import android.graphics.Paint.Style
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.TextButton
import androidx.compose.material.primarySurface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.Navigation
import ggv.ayush.bcatrack.BcaStudents
import ggv.ayush.bcatrack.BscStudents
import okhttp3.internal.wait

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun SubmitDialog(dialogOpen : MutableState<Boolean>, course: String?){
    val context = LocalContext.current

    val students = when (course) {
        "BCA" -> BcaStudents
        "BSC" -> BscStudents
        else -> emptyList()
    }
    if(dialogOpen.value){
        //Show dialog
        AlertDialog(
            onDismissRequest = {
                   dialogOpen.value = false
            },
            confirmButton = {
                Button(onClick = {
                    val fileUri = generateAndDownloadExcelFile(context, students , course!!)
                    shareFile(context, fileUri, "students.xlsx")
                    clearSelectedStudents(students)
                    dialogOpen.value = false
                }) {
                    Text("Confirm" , color =  Color.White)
                }
            },
            dismissButton = {
                Button(onClick = { dialogOpen.value = false }) {
                    Text("Cancel" , color =  Color.White)
                }
            },
            //text style should be bold
            title = { Text(text = "Confirm Submission" , style = TextStyle(fontWeight = FontWeight.Bold)   , color = Color.Black)   },
            text = { Text("Are you sure you want to submit?"   , color = Color.Black) },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp),
            shape = RoundedCornerShape(30.dp),
            backgroundColor = Color.White,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )

        )

    }

}