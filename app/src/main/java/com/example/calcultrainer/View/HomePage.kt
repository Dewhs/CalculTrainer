package com.example.calcultrainer.View

import android.service.notification.NotificationListenerService.Ranking
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.calcultrainer.R
import com.example.calcultrainer.View.theme.Chill
import com.example.calcultrainer.View.theme.Heading1
import com.example.calcultrainer.View.theme.Heading2
import com.example.calcultrainer.View.theme.Heading2_Btn
import com.example.calcultrainer.View.theme.Heading3
import com.example.calcultrainer.View.theme.Heading4
import com.example.calcultrainer.View.theme.Light


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomePage(NavigateToCalculPage: () -> Unit) {
    Column(
        modifier = Modifier
            .background(Light)
            .fillMaxSize()
            .padding(top = 80.dp, bottom = 40.dp),
        verticalArrangement = Arrangement.spacedBy(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_without_font),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(5.dp))
            BasicText(text = "Calcul Trainer", style = Heading1)
        }
        val Levels = listOf<String>(
            "Chill",
            "Training",
            "Infinite"
        )

        val pagerState = rememberPagerState()

        Box(
            modifier = Modifier
                .fillMaxWidth(),

        ) {
            HorizontalPager(
                pageCount = Levels.size,
                state = pagerState,
                key = { Levels[it] }
            ) {
                /*index ->
            TestCaroussel(name = Levels[index])*/
                ChillPresBody()

            }
        }


        //ChillPresBody()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 40.dp, end = 40.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = NavigateToCalculPage,
                colors = ButtonDefaults.buttonColors(Chill)
            ) {
                Row(
                    modifier = Modifier
                        .padding(top = 5.dp, bottom = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.play_chill),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    BasicText(text = "Start Chill", style = Heading2_Btn)
                }
            }
        }
    }
}


@Composable
fun TestCaroussel(name: String) {
    Column {
        BasicText(text = name)
    }
}


@Composable
fun ChillPresBody() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
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
                text = "The Chill game mode is designed for a short session in a more relaxed format, and without a timer.",
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
