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
        description = "NarutoOG is a fan made app for Naruto fans. It provides you with all the information about Naruto and its characters."
    )

    object SecondPage : onBoardingPage(
        image = R.drawable.explore ,
        title = "Explore",
        description = "You can take Attendance of students"
    )

    object ThirdPage : onBoardingPage(
        image = R.drawable.power ,
        title = "Power",
        description = "Just Click the Attendance Button and you are good to go."
    )

}