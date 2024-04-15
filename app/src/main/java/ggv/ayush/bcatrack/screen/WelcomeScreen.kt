package ggv.ayush.bcatrack.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import ggv.ayush.bcatrack.domain.model.onBoardingPage
import ggv.ayush.bcatrack.ui.theme.DarkGrey
import ggv.ayush.bcatrack.ui.theme.LARGE_PADDING
import ggv.ayush.bcatrack.ui.theme.LightGrey
import ggv.ayush.bcatrack.ui.theme.PAGING_INDICATOR_WIDTH
import ggv.ayush.bcatrack.ui.theme.Purple500
import ggv.ayush.bcatrack.ui.theme.Purple700
import ggv.ayush.bcatrack.ui.theme.welcomeScreenBackgroundColor
import ggv.ayush.bcatrack.util.Constants.LAST_ON_BOARDING_PAGE_INDEX
import ggv.ayush.bcatrack.util.Constants.ON_BOARDING_PAGE_COUNT

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen(
    navController: NavController,
    welcomeViewModel: WelcomeViewModel = hiltViewModel()
){

    val activeColor = if (isSystemInDarkTheme()) Purple700 else Purple500
    val inActiveColor = if (isSystemInDarkTheme()) DarkGrey else LightGrey


    val pages = listOf(
        onBoardingPage.FirstPage,
        onBoardingPage.SecondPage,
        onBoardingPage.ThirdPage,
    )
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = if (isSystemInDarkTheme()) Color.Black else MaterialTheme.colors.welcomeScreenBackgroundColor),

    ) {
        HorizontalPager(
            modifier = Modifier
                .weight(10f),
            count = ON_BOARDING_PAGE_COUNT,
            state = pagerState,
            verticalAlignment = Alignment.CenterVertically

        ) { position ->
            PagerScreen(onBoardingPage = pages[position])
        }
        HorizontalPagerIndicator(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .weight(1f),
            pagerState = pagerState,
            activeColor = activeColor,
            inactiveColor = inActiveColor,
            indicatorWidth = PAGING_INDICATOR_WIDTH,
            spacing = PAGING_INDICATOR_WIDTH,
        )

        FinishButton(
            modifier = Modifier
                .weight(1f),
            pagerState = pagerState,

        ){
            navController.popBackStack()
            navController.navigate("main")
            welcomeViewModel.saveOnBoardingState(true)
        }

    }

}

@Composable
fun PagerScreen(onBoardingPage: onBoardingPage) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
    ) {
        val txtColorTitle = if (isSystemInDarkTheme()) LightGrey else DarkGrey
        val txtColorDesc = if (isSystemInDarkTheme()) LightGrey.copy(alpha = 0.5f) else DarkGrey.copy(alpha = 0.5f)

        Image(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.7f),
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = null
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(0.8f) ,
            text = onBoardingPage.title,
            color = txtColorTitle,
            fontSize = MaterialTheme.typography.h4.fontSize,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h4,
            textAlign = TextAlign.Center

        )
        Text(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(top = LARGE_PADDING),
            text = onBoardingPage.description,
            color = txtColorDesc,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = MaterialTheme.typography.body1.fontWeight,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center
        )



        


    }
}

@Composable
@Preview(showBackground = true)
fun FirstOnBoardingScreenPreview(){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
    ) {
        PagerScreen(onBoardingPage = onBoardingPage.FirstPage)

    }

}
@Composable
fun FinishButton(
    modifier: Modifier ,
    pagerState: PagerState,
    onClick: () -> Unit
){
    Row(
        modifier = modifier
            .padding(horizontal = LARGE_PADDING)
            .fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center
    ){
        AnimatedVisibility(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            visible = pagerState.currentPage == LAST_ON_BOARDING_PAGE_INDEX
        ) {
            Button(
                onClick = { onClick.invoke() },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if(isSystemInDarkTheme()) Purple700 else Purple500,
                    contentColor = Color.White
                ),
            ) {
                Text(text = "Finish")
            }
        }
    }
}