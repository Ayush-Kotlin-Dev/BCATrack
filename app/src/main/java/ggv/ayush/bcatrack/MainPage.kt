package ggv.ayush.bcatrack

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ggv.ayush.Student
import ggv.ayush.bcatrack.screen.home.HomeTopBar
import ggv.ayush.bcatrack.ui.theme.Purple500
import ggv.ayush.bcatrack.ui.theme.Purple700
import kotlinx.coroutines.launch
import org.apache.poi.xssf.usermodel.XSSFWorkbook



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun MainPage() {
    val pagerState = rememberPagerState()
    val isDarkTheme = isSystemInDarkTheme()
    val backgroundColor = if (isDarkTheme) Color.Black else Color.White
    //status bar color
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = if (isSystemInDarkTheme()) Color.Black else Purple700
    )

    val logoPosition = remember { Animatable(0f) }
    LaunchedEffect(key1 = true) {
        logoPosition.animateTo(
            targetValue = -350f,  // Adjust this value as needed
            animationSpec = tween(400, easing = Easing { fraction ->
                LinearEasing.transform(fraction)
            })
        )
    }

    Scaffold(
        topBar = {
            HomeTopBar(
                logoPosition = logoPosition.value
            )
        }
    ){
        Column(
            modifier = Modifier.padding(all = 25.dp)
        ) {
            Column(
                modifier = Modifier
                    .height(500.dp)
                    .fillMaxWidth()
                    .background(backgroundColor),
            ) {
                // Swipable card interface
                HorizontalPager(
                    count = students.size,
                    state = pagerState,
                    modifier = Modifier.fillMaxSize()
                ) { page ->
                    val student = students[page]
                    StudentCard(
                        student = student,
                        onPresentClicked = { student.presentState.value = true }
                    )
                }

            }
            // Navigation buttons
            val isDarkTheme = isSystemInDarkTheme()
            val backgroundColor = if (isDarkTheme) Color.Black else Color.White
            val textColor = if (isDarkTheme) Color.White else Color.Black
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 100.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,

                ) {
                val coroutineScope = rememberCoroutineScope()
                Button(onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                }  ,colors = ButtonDefaults.buttonColors(backgroundColor),
                    modifier = Modifier.align(Alignment.CenterVertically).clip(RoundedCornerShape(0))) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )

                }
                val context = LocalContext.current
                Button(onClick = {
                    val fileUri = generateAndDownloadExcelFile(context, students)
                    shareFile(context, fileUri, "students.xlsx")
                }  ,colors = ButtonDefaults.buttonColors(backgroundColor),
                    modifier = Modifier.align(Alignment.CenterVertically).clip(RoundedCornerShape(0))) {
                    Text(text = "Submit" , color = textColor)
                }

                Button(onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }

                }  ,colors = ButtonDefaults.buttonColors(backgroundColor),
                    modifier = Modifier.align(Alignment.CenterVertically).clip(RoundedCornerShape(0))
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null
                    )
                }

            }
        }
    }
    }




@RequiresApi(Build.VERSION_CODES.O)
fun generateAndDownloadExcelFile(context: Context, students: List<Student>)  : Uri{
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
        row.createCell(0).setCellValue(student.rollNumber.toDouble()) // Roll Number is now the first column
        row.createCell(1).setCellValue(student.name) // Name is now the second column
        row.createCell(2).setCellValue(if (student.presentState.value) "Yes" else "No")
    }

    val resolver = context.contentResolver
    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, "BCA_$formattedDate.xlsx")
        put(MediaStore.MediaColumns.MIME_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
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