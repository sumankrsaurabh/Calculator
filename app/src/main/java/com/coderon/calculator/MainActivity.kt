package com.coderon.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.coderon.calculator.ui.theme.AppTheme
import com.coderon.calculator.ui.theme.Cyan

@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {

    private val viewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorScheme.primary
                ) {
                    val calculatorButtons = remember {
                        mutableListOf(
                            CalculatorButton("AC", CalculatorButtonType.Reset),
                            CalculatorButton("AC", CalculatorButtonType.Reset),
                            CalculatorButton("()", CalculatorButtonType.Action),

                            CalculatorButton("÷", CalculatorButtonType.Action),

                            CalculatorButton("7", CalculatorButtonType.Normal),
                            CalculatorButton("8", CalculatorButtonType.Normal),
                            CalculatorButton("9", CalculatorButtonType.Normal),

                            CalculatorButton("×", CalculatorButtonType.Action),


                            CalculatorButton("4", CalculatorButtonType.Normal),
                            CalculatorButton("5", CalculatorButtonType.Normal),
                            CalculatorButton("6", CalculatorButtonType.Normal),

                            CalculatorButton("−", CalculatorButtonType.Action),

                            CalculatorButton("1", CalculatorButtonType.Normal),
                            CalculatorButton("2", CalculatorButtonType.Normal),
                            CalculatorButton("3", CalculatorButtonType.Normal),

                            CalculatorButton("+", CalculatorButtonType.Action),

                            CalculatorButton(
                                icon = Icons.Rounded.Refresh,
                                type = CalculatorButtonType.Reset
                            ),
                            CalculatorButton("0", CalculatorButtonType.Normal),
                            CalculatorButton("∙", CalculatorButtonType.Normal),

                            CalculatorButton("=", CalculatorButtonType.Action),
                        )
                    }

                    val (uiText, setUiText) = remember {
                        mutableStateOf("0")
                    }

                    val (input, setInput) = remember {
                        mutableStateOf<String?>(null)
                    }

                    LaunchedEffect(uiText) {
                        if (uiText.startsWith("0") && uiText != "0") {
                            setUiText(uiText.substring(1))
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Column {
                            Text(
                                text = uiText,
                                fontSize = 48.sp,
                                fontWeight = FontWeight.Bold,
                                color = colorScheme.onPrimary,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                            Spacer(modifier = Modifier.heightIn(32.dp))
                            LazyVerticalGrid(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                                    .background(colorScheme.secondary)
                                    .padding(8.dp),
                                columns = GridCells.Fixed(count = 4),
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                contentPadding = PaddingValues(16.dp)
                            ) {
                                items(calculatorButtons) {
                                    CalcButton(it = it,
                                        onClick = {
                                            when (it.type) {
                                                CalculatorButtonType.Normal -> {
                                                    runCatching {
                                                        setUiText(
                                                            uiText.toInt().toString() + it.text
                                                        )
                                                    }.onFailure { _ ->
                                                        setUiText(uiText + it.text)
                                                    }
                                                    setInput((input ?: "") + it.text)
                                                    if (viewModel.action.value.isNotEmpty()) {
                                                        if (viewModel.secondNumber.value == null) {
                                                            viewModel.setSecondNumber(it.text!!.toDouble())
                                                        } else {
                                                            if (viewModel.secondNumber.value.toString()
                                                                    .split(".")[1] == "0"
                                                            ) {
                                                                viewModel.setSecondNumber(
                                                                    (viewModel.secondNumber.value.toString()
                                                                        .split(".")
                                                                        .first() + it.text).toDouble()
                                                                )

                                                            } else {
                                                                viewModel.setSecondNumber((viewModel.secondNumber.value.toString() + it.text).toDouble())
                                                            }
                                                        }
                                                    }
                                                }

                                                CalculatorButtonType.Action -> {
                                                    if (it.text == "=") {
                                                        val result = viewModel.getResult()
                                                        setUiText(result.toString())
                                                        setInput(null)
                                                        viewModel.resetAll()
                                                    } else {
                                                        runCatching {
                                                            setUiText(
                                                                uiText.toInt().toString() + it.text
                                                            )
                                                        }.onFailure { _ ->
                                                            setUiText(uiText + it.text)
                                                        }
                                                        if (input != null) {
                                                            if (viewModel.firstNumber.value == null) {
                                                                viewModel.setFirstNumber(input.toDouble())
                                                            } else {
                                                                viewModel.setSecondNumber(input.toDouble())
                                                            }
                                                            viewModel.setAction(it.text!!)
                                                            setInput(null)
                                                        }
                                                    }
                                                }

                                                CalculatorButtonType.Reset -> {
                                                    setUiText("0")
                                                    setInput(null)
                                                    viewModel.resetAll()
                                                }
                                            }
                                        })
                                }
                            }
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun CalcButton(it: CalculatorButton, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(colorScheme.primary)
            .aspectRatio(1f)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        val contentColor = when (it.type) {
            CalculatorButtonType.Normal -> colorScheme.onPrimary
            CalculatorButtonType.Action -> com.coderon.calculator.ui.theme.Red
            else -> {
                Cyan
            }
        }
        if (it.text != null) {
            Text(
                text = it.text,
                color = contentColor,
                fontWeight = FontWeight.Bold,
                fontSize = if (it.type == CalculatorButtonType.Action) 25.sp else 20.sp
            )
        } else {
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = it.icon!!,
                contentDescription = null,
                tint = contentColor
            )
        }
    }
}