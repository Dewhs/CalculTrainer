package com.example.calcultrainer.ui


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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.shadow
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
import com.example.calcultrainer.ui.theme.Dark
import com.example.calcultrainer.ui.theme.NavBarItemLabelStyle
import kotlinx.coroutines.launch


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
                    .fillMaxWidth().shadow(
                        elevation = 15.dp,
                        shape = RoundedCornerShape(30.dp),
                        spotColor = currentMode.darkColor

                    ),
                onClick = navigateToCalculPage,
                colors = ButtonDefaults.buttonColors(currentMode.lightColor)
            ) {
                Row(
                    modifier = Modifier
                        .padding(top = 5.dp, bottom = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = currentMode.playPath),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(currentMode.darkColor)
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

            // ------------------ TAB_BAR -------------------
            Column(
                modifier = Modifier.fillMaxWidth().shadow(
                    elevation = 15.dp,
                    spotColor = currentMode.darkColor
                )
            ) {


                NavigationBar(
                    containerColor = Light,
                    tonalElevation = 0.dp
                ) {
                    for ((index, lvlDesc) in Levels.withIndex()) {
                        val isSelected = pagerState.currentPage == index
                        NavigationBarItem(
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = Color.White,
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

            /*Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.15f),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Btn_Carousel_Mode(level = Levels[0], scope = scope, pagerState = pagerState)
                Btn_Carousel_Mode(level = Levels[1], scope = scope, pagerState = pagerState)
                Btn_Carousel_Mode(level = Levels[2], scope = scope, pagerState = pagerState)

            }*/
            //Spacer(modifier = Modifier.padding(20.dp))


        }

    }
}


/*@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Btn_Carousel_Mode(
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
}*/



@Composable
fun PresBody(level: LevelDesc) {
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
                text = level.description,
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