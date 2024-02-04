package com.example.calcultrainer.View

import android.service.notification.NotificationListenerService.Ranking
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.calcultrainer.R
import com.example.calcultrainer.View.theme.Chill
import com.example.calcultrainer.View.theme.Heading1
import com.example.calcultrainer.View.theme.Heading2
import com.example.calcultrainer.View.theme.Heading4
import com.example.calcultrainer.View.theme.Light
import com.example.calcultrainer.View.theme.Training
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.example.calcultrainer.Model.DC_Level
import com.example.calcultrainer.Model.Levels
import com.example.calcultrainer.View.theme.Heading2_Btn_Chill


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomePage(NavigateToCalculPage: () -> Unit) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .background(Light)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 120.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_without_font),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(5.dp))
            BasicText(text = "Calcul Trainer", style = Heading1)
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {


            Spacer(modifier = Modifier.weight(0.1f))

            HorizontalPager(
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .weight(1f),
                pageCount = Levels.size,
                state = pagerState,
                key = { Levels[it].name }
            ) { index ->
                PresBody(level = Levels[index])
            }


            Button(
                modifier = Modifier
                    .padding(start = 40.dp, end = 40.dp)
                    .fillMaxWidth(),
                onClick = NavigateToCalculPage,
                colors = ButtonDefaults.buttonColors(Levels[pagerState.currentPage].color)
            ) {
                Row(
                    modifier = Modifier
                        .padding(top = 5.dp, bottom = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = Levels[pagerState.currentPage].play_path),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    BasicText(
                        modifier = Modifier.padding(top = 3.dp),
                        text = "Start ${Levels[pagerState.currentPage].name}",
                        style = Levels[pagerState.currentPage].Heading2_btn
                    )
                }
            }
            Spacer(modifier = Modifier.padding(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.15f),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Btn_Caroussel_Mode(level = Levels[0], scope = scope, pagerState = pagerState)
                Btn_Caroussel_Mode(level = Levels[1], scope = scope, pagerState = pagerState)
                Btn_Caroussel_Mode(level = Levels[2], scope = scope, pagerState = pagerState)

            }
            Spacer(modifier = Modifier.padding(20.dp))


        }

    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Btn_Caroussel_Mode(
    level: DC_Level,
    scope: CoroutineScope,
    pagerState: PagerState
) {
    var opacity = 0.5
    var pageDestination = 0

    if (Levels[pagerState.currentPage].name == level.name) {
        opacity = 1.0
    }

    if (level.name == "Chill") {
        pageDestination = 0
    }
    if (level.name == "Training") {
        pageDestination = 1
    }
    if (level.name == "Infinite") {
        pageDestination = 2 
    }


    Button(
        modifier = Modifier
            .alpha(opacity.toFloat()),
        onClick = {
            scope.launch {
                pagerState.animateScrollToPage(pageDestination)
            }
        },
        colors = ButtonDefaults.buttonColors(level.color)
    ) {

        Image(
            painter = painterResource(id = level.path),
            contentDescription = null
        )
    }
}


@Composable
fun PresBody(level: DC_Level) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 40.dp, end = 40.dp),
        verticalArrangement = Arrangement.spacedBy(35.dp)
    ) {
        //DESCRIPTION
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            BasicText(text = "Description", style = Heading1)
            BasicText(
                text = level.Description,
                style = Heading4
            )
        }

        //RANK
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            BasicText(text = "Rank", style = Heading1)
            Top3Ranking()
        }

    }
}


@Composable
fun Top3Ranking() {
    Column(
        modifier = Modifier
            .padding(20.dp),
    ) {

        RankingLine(rank = 1, name = "Albert", points = 2541)
        RankingLine(rank = 2, name = "Kaarismmmaticc", points = 2538)
        RankingLine(rank = 3, name = "Robert", points = 2213)

    }
}

@Composable
fun RankingLine(rank: Int, name: String, points: Int) {
    var nameToPrint = name
    if (nameToPrint.length > 10) {
        nameToPrint = nameToPrint.take(10) + "..."
    }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BasicText(text = "$rank. $nameToPrint", style = Heading2)
        BasicText(text = "$points pts", style = Heading2)
    }

}
