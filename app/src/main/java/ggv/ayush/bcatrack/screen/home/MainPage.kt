package ggv.ayush.bcatrack.screen.home

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ggv.ayush.bcatrack.R
import ggv.ayush.bcatrack.Student
import ggv.ayush.bcatrack.students
import ggv.ayush.bcatrack.ui.theme.Purple700
import kotlinx.coroutines.launch
import org.apache.poi.xssf.usermodel.XSSFWorkbook


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun MainPage() {
    val isDarkTheme = isSystemInDarkTheme()
    //status bar color
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = if (isSystemInDarkTheme()) Color.Black else Purple700
    )
    //status bar content color
    systemUiController.setSystemBarsColor(
        color = if (isSystemInDarkTheme()) Purple700 else Purple700
    )
    var showDialog = remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            HomeTopBar(
            )
        }
    ) {
        Box {
            BackgroundImage(
                painter = painterResource(id = R.drawable.img1),
                contentDescription = "Background Image",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,

                ) {
                StudentList(students = students)
                val isDarkTheme = isSystemInDarkTheme()
                val backgroundColor = if (isDarkTheme) Color.White else Color.Red
                val textColor = if (isDarkTheme) Color.Black else Color.White


                val context = LocalContext.current
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        showDialog.value = true
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor),
                    modifier = Modifier
                        .clip(RoundedCornerShape(10)),


                    ) {
                    Text(text = "Submit", color = textColor)
                }
            }

            SubmitDialog(showDialog)


        }
    }
}

@Composable
fun BackgroundImage(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.FillBounds,
) {
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale,
    )
}

fun clearSelectedStudents(students: List<Student>) {
    students.forEach { student ->
        student.presentState.value = false
        student.clickState.value = 0 // Add this line to reset the clickState

    }
}


@RequiresApi(Build.VERSION_CODES.Q)
fun generateAndDownloadExcelFile(context: Context, students: List<Student>): Uri {
    val workbook = XSSFWorkbook()
    val sheet = workbook.createSheet("Students")

    val currentDate = java.time.LocalDate.now()
    val formatter = java.time.format.DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy")
    val formattedDate = currentDate.format(formatter)

    val headerRow = sheet.createRow(0)
    headerRow.createCell(0).setCellValue("Roll Number") // Roll Number is now the first column
    headerRow.createCell(1).setCellValue("Name") // Name is now the second column
    headerRow.createCell(2).setCellValue("Present")

    val dateCellStyle = workbook.createCellStyle()
    val font = workbook.createFont()
    font.bold = true
    dateCellStyle.setFont(font)

    val dateCell = headerRow.createCell(3)
    dateCell.setCellValue("Date: $formattedDate")
    dateCell.setCellStyle(dateCellStyle)

    students.forEachIndexed { index, student ->
        val row = sheet.createRow(index + 1)
        row.createCell(0)
            .setCellValue(student.rollNumber.toDouble()) // Roll Number is now the first column
        row.createCell(1).setCellValue(student.name) // Name is now the second column
        row.createCell(2).setCellValue(if (student.presentState.value) "Yes" else "No")
    }

    val resolver = context.contentResolver
    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, "BCA_$formattedDate.xlsx")
        put(
            MediaStore.MediaColumns.MIME_TYPE,
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
        )
        put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
    }

    val uri = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)

    resolver.openOutputStream(uri!!).use { outputStream ->
        workbook.write(outputStream)
    }

    return uri
}

fun shareFile(context: Context, fileUri: Uri, fileName: String) {
    val intent = Intent().apply {
        action = Intent.ACTION_SEND
        type = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
        putExtra(Intent.EXTRA_STREAM, fileUri)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }

    context.startActivity(Intent.createChooser(intent, "Share $fileName using"))
}