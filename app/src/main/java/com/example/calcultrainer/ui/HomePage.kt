package com.example.calcultrainer.ui


import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.calcultrainer.R
import com.example.calcultrainer.ui.theme.Heading1
import com.example.calcultrainer.ui.theme.Heading2
import com.example.calcultrainer.ui.theme.Heading4
import com.example.calcultrainer.ui.theme.Light
import com.example.calcultrainer.Model.LevelDesc
import com.example.calcultrainer.Model.Levels
import com.example.calcultrainer.ui.customs.NoRippleInteractionSource
import com.example.calcultrainer.ui.customs.advancedShadow
import com.example.calcultrainer.ui.theme.Dark
import com.example.calcultrainer.ui.theme.LightGray
import com.example.calcultrainer.ui.theme.NavBarItemLabelStyle
import kotlinx.coroutines.launch
import java.util.logging.Level


@SuppressLint("NewApi")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomePage(navigateToCalculPage: () -> Unit) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .background(Light)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        // ------------------ HEAD -------------------
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
            val currentMode = Levels[pagerState.currentPage]

            Spacer(modifier = Modifier.weight(0.1f))
            // ------------------ Carousel -------------------
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

            // ------------------ BTN_START -------------------
            Button(
                modifier = Modifier
                    .padding(start = 40.dp, end = 40.dp)
                    .fillMaxWidth(),
                onClick = navigateToCalculPage,
                colors = ButtonDefaults.buttonColors(currentMode.lightColor)
            ) {
                Row(
                    modifier = Modifier.padding(vertical = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = currentMode.playPath),
                        colorFilter = ColorFilter.tint(currentMode.darkColor),
                        contentDescription = "start button",
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        modifier = Modifier.padding(top = 3.dp),
                        text = "Start ${currentMode.name}",
                        style = currentMode.textStyle,
                        color = currentMode.darkColor
                    )
                }
            }
            Spacer(modifier = Modifier.padding(40.dp))

            // ------------------ NAV_BOTTOM_BAR -------------------
            NavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .advancedShadow(
                        color = currentMode.darkColor,
                        alpha = 0.15f,
                        shadowBlurRadius = 50.dp
                    ),
                containerColor = Light,
            ) {
                for ((index, lvlDesc) in Levels.withIndex()) {
                    val isSelected = pagerState.currentPage == index
                    NavigationBarItem(
                        interactionSource = NoRippleInteractionSource(),
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Light,
                            selectedIconColor = currentMode.darkColor,
                            selectedTextColor = currentMode.darkColor,
                            unselectedIconColor = Dark,
                            unselectedTextColor = Dark
                        ),
                        selected = isSelected,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = lvlDesc.path),
                                contentDescription = null,
                            )
                        },
                        label = {
                            Text(
                                text = lvlDesc.name,
                                style = NavBarItemLabelStyle,
                            )
                        },
                        alwaysShowLabel = false
                    )
                }
            }
        }
    }
}


@Composable
fun PresBody(level: LevelDesc) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 40.dp, end = 40.dp),
    ) {
        BasicText(text = "Description", style = Heading1)
        BasicText(
            text = level.description,
            style = Heading4
        )

        Spacer(modifier = Modifier.size(35.dp))
        when(level.name){
            "Chill" -> {
                BasicText(text = "Stats", style = Heading1)
            }
            "Infinite" -> {
                BasicText(text = "Rank", style = Heading1)
                Top3Ranking()
            }
            "Multiplayer" -> {
                BasicText(text = "Last game ranking", style = Heading1)
                Top3Ranking()
            }
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




