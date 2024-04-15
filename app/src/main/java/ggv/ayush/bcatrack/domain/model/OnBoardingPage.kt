package ggv.ayush.bcatrack.domain.model

import androidx.annotation.DrawableRes
import ggv.ayush.bcatrack.R

sealed class onBoardingPage(
    @DrawableRes
    val image : Int,
    val title : String,
    val description : String
){
    object FirstPage : onBoardingPage(
        image = R.drawable.greetings ,
        title = "Greetings",
        description = "Hello Learners."
    )

    object SecondPage : onBoardingPage(
        image = R.drawable.explore ,
        title = "Explore",
        description = "You can take Attendance of students , by clicking on the student Name Row."
    )

    object ThirdPage : onBoardingPage(
        image = R.drawable.power ,
        title = "Power",
        description = "After Marking Attendance , You can tap Submit button to get Excel File ."
    )

}