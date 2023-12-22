package com.example.calcultrainer.View

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.example.calcultrainer.View.theme.Dark
import com.example.calcultrainer.View.theme.Heading1
import com.example.calcultrainer.View.theme.Heading2
import com.example.calcultrainer.View.theme.Light
import com.example.calcultrainer.View.theme.LightGray
import com.example.calcultrainer.View.theme.ResultStyle

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CalculPage() {
    val keyboardController = LocalSoftwareKeyboardController.current
    var result by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
        //.background(Color.Red)
    ) {


        BasicText(text = "587 + 342", style = Heading1)
        Spacer(modifier = Modifier.size(15.dp))
        Box(
            modifier = Modifier
                .border(BorderStroke(5.dp, LightGray), shape = RoundedCornerShape(100))
                .height(5.dp)
                .width(350.dp),
        )
        Spacer(modifier = Modifier.size(25.dp))
        CustomNumberInput()

    }
}

@Composable
fun CustomNumberInput() {
    val size = 3
    var result by remember { mutableStateOf("") }

    BasicTextField(
        value = result,
        singleLine = true,
        onValueChange = { newValue ->
            if (newValue.length <= size) {
                result = newValue
            }
        },
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
                                BorderStroke(borderSize.dp, Color.Black),
                                shape = RoundedCornerShape(30)
                            )
                            .height(50.dp)
                            .width(40.dp)
                            .background(Light)
                    )
                    {
                        Box(
                            modifier = Modifier.fillMaxSize(),

                        ) {
                            BasicText(text = nb, style = Heading1, modifier = Modifier.align(
                                Alignment.Center))
                            Box(modifier = Modifier
                                .height(isVisible * 10.dp)
                                .width(25.dp)
                                .background(Dark)
                                .align(alignment = Alignment.BottomCenter)
                                .padding(start = 0.dp, end = 0.dp, top = 0.dp, bottom = 15.dp)
                            )

                        }
                    }
                }
            }

        }


    }
}