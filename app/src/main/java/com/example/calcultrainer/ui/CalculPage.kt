package com.example.calcultrainer.ui

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.calcultrainer.Model.msgHistorique
import com.example.calcultrainer.R
import com.example.calcultrainer.ui.theme.Chill
import com.example.calcultrainer.ui.theme.Dark
import com.example.calcultrainer.ui.theme.Heading1
import com.example.calcultrainer.ui.theme.Heading2
import com.example.calcultrainer.ui.theme.Heading3
import com.example.calcultrainer.ui.theme.Infinite
import com.example.calcultrainer.ui.theme.Light
import com.example.calcultrainer.ui.theme.LightGray
import com.example.calcultrainer.ui.theme.false_msgHistorique_H
import com.example.calcultrainer.ui.theme.true_msgHistorique_H
import com.example.calcultrainer.ViewModel.CalculPageViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CalculPage(
    navHostController: NavHostController,
    viewModel: CalculPageViewModel = viewModel()
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var result by remember { mutableStateOf("") }

    var equal by remember { mutableStateOf(false) }
    val listSate = rememberLazyListState()

    val listCorrection: List<msgHistorique> = viewModel.correction

    val focusRequester = remember {
        FocusRequester()
    }
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(focusRequester) {
        focusRequester.requestFocus()

    }
    
    Column(
        modifier = Modifier
            .padding(horizontal = 40.dp, vertical = 25.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    )
    {
        IconButton(
            onClick = {
                navHostController.popBackStack()
                println("Back Clicked !")
            },
            content = {
                Image(
                    painter = painterResource(id = R.drawable.left_arrow),
                    contentDescription = null
                )

            },
            modifier = Modifier.padding(top = 40.dp)
        )


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            BasicText(text = viewModel.calcul, style = Heading1)
            Spacer(modifier = Modifier.size(15.dp))
            Box(
                modifier = Modifier
                    .border(BorderStroke(5.dp, LightGray), shape = RoundedCornerShape(100))
                    .height(5.dp)
                    .width(350.dp),
            )
            Spacer(modifier = Modifier.size(25.dp))

            //CustomNumberInput()

            val size = viewModel.resultSize





            BasicTextField(
                value = result,
                singleLine = true,
                onValueChange = { newValue ->
                    if (newValue.length <= size && !newValue.contains(" ") && !newValue.contains("-") && !newValue.contains(",") && !newValue.contains(".")) {
                        result = newValue

                        if (newValue.length == size) {
                            equal = viewModel.checkResult(result.toInt())
                            result = ""


                            coroutineScope.launch {
                                listSate.animateScrollToItem(index = listCorrection.size)
                            }
                        }
                    }

                },
                modifier = Modifier.focusRequester(focusRequester),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    repeat(size) { index ->
                        var borderSize = 1
                        var isVisible = 0
                        val nb = when {
                            index >= result.length -> ""
                            else -> result[index].toString()
                        }
                        if (index == result.length) {
                            borderSize = 3
                            isVisible = 1
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Box(
                                modifier = Modifier
                                    .border(
                                        BorderStroke(borderSize.dp, Dark),
                                        shape = RoundedCornerShape(30)
                                    )
                                    .height(50.dp)
                                    .width(40.dp)
                                    .background(Light)
                            ) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),

                                    ) {
                                    BasicText(
                                        text = nb, style = Heading1, modifier = Modifier.align(
                                            Alignment.BottomCenter
                                        )
                                    )
                                    Box(
                                        modifier = Modifier
                                            .height(isVisible * 25.dp)
                                            .fillMaxWidth()
                                            .align(alignment = Alignment.BottomCenter)

                                    ) {
                                        val infiniteTransition =
                                            rememberInfiniteTransition(label = "")
                                        val color by infiniteTransition.animateColor(
                                            initialValue = Light,
                                            targetValue = Dark,
                                            animationSpec = infiniteRepeatable(
                                                animation = tween(500, easing = EaseIn),
                                                repeatMode = RepeatMode.Reverse
                                            ), label = ""
                                        )
                                        Box(
                                            modifier = Modifier
                                                .height(5.dp)
                                                .width(20.dp)
                                                .background(color)
                                                .align(alignment = Alignment.Center)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        /*BasicText(
            text = equal.toString(),
            style = Heading4,
            modifier = Modifier
                .imePadding()
                .align(Alignment.End)
        )*/

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom) {
            BasicText(modifier = Modifier.imePadding(),text = "Level : ${viewModel.level}", style = Heading2)

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .imePadding()
                    //.align(Alignment.End)
                    .height(150.dp),
                state = listSate,
            ) {
                items(listCorrection) { _msgHistorique ->

                    //BasicText(text = correct, style = Heading3)
                    printMsgHistorique(msg = _msgHistorique)
                }
            }
        }
    }
}


@Composable
fun printMsgHistorique(msg: msgHistorique) {
    var color: Color = LightGray
    var style: androidx.compose.ui.text.TextStyle = Heading3

    if (!msg.isCorrection) {
        if (msg.isTrue) {
            color = Chill
            style = true_msgHistorique_H
        } else {
            color = Infinite
            style = false_msgHistorique_H
        }
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(100.dp))
            .background(color)
    )
    {
        BasicText(
            text = msg.value,
            style = style,
            modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp)
        )
    }

    /*Box(
        modifier = Modifier
            .size(100.dp)
            .border(
                border = BorderStroke(1.dp, color),
                RoundedCornerShape(100.dp)
            )
            .background(Color.Red)

    )
    {
        BasicText(text = msg.value, style = style, modifier = Modifier.padding(10.dp))
    }*/


}
