package com.coderon.calculator

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.coderon.calculator.ui.theme.Black200
import com.coderon.calculator.ui.theme.Black500
import com.coderon.calculator.ui.theme.Cyan
import com.coderon.calculator.ui.theme.DarkWhite
import com.coderon.calculator.ui.theme.Green
import com.coderon.calculator.ui.theme.Red

@Composable
fun CalculatorUI(
    viewModel: CalculatorViewModel,
) {
    val expression = viewModel.expression
    val buttonSpacing = 16.dp
    val darkMode = isSystemInDarkTheme()

    Log.d("MainActivity", "onCreate: ${viewModel.expression.value}")
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing),
        ) {
            LazyRow(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth(),
                reverseLayout = true
            ) {
                item {
                    Text(
                        text = expression.value.ifEmpty { "0" },
                        textAlign = TextAlign.End,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 32.dp, horizontal = 16.dp),
                        fontWeight = FontWeight.Light,
                        fontSize = 64.sp,
                        color = if (darkMode) White else Black200,
                        maxLines = 1
                    )
                }
            }
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(topEnd = 32.dp, topStart = 32.dp))
                    .background(if (darkMode) Black500 else DarkWhite)
                    .padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(buttonSpacing),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(
                        symbol = "AC",
                        color = if (darkMode) Black200 else White,
                        textStyle = TextStyle(fontWeight = FontWeight.Bold, color = Green),
                        modifier = Modifier
                            .height(64.dp)
                            .weight(1f)
                            .clickable {
                                viewModel.clear()
                            }
                    )

                    CalculatorButton(
                        symbol = "(",
                        color = if (darkMode) Black200 else White,
                        textStyle = TextStyle(fontWeight = FontWeight.Bold, color = Green),
                        modifier = Modifier
                            .height(64.dp)
                            .weight(1f)
                            .clickable {
                                viewModel.append("(")
                            }
                    )
                    CalculatorButton(
                        symbol = ")",
                        color = if (darkMode) Black200 else White,
                        textStyle = TextStyle(fontWeight = FontWeight.Bold, color = Green),
                        modifier = Modifier
                            .height(64.dp)
                            .weight(1f)
                            .clickable {
                                viewModel.append(")")
                            }
                    )

                    CalculatorIconButton(
                        symbol = R.drawable.divide_solid,
                        color = if (darkMode) Black200 else White,
                        tint = Green,
                        modifier = Modifier
                            .height(64.dp)
                            .weight(1f)
                            .clickable {
                                viewModel.append("÷")
                            }
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(
                        symbol = "7",
                        color = if (darkMode) Black200 else White,
                        textStyle = TextStyle(fontWeight = FontWeight.Bold, color = if (darkMode) White else Black200),
                        modifier = Modifier
                            .height(64.dp)
                            .weight(1f)
                            .clickable {
                                viewModel.append("7")
                            }
                    )
                    CalculatorButton(
                        symbol = "8",
                        textStyle = TextStyle(fontWeight = FontWeight.Bold, color = if (darkMode) White else Black200),
                        color = if (darkMode) Black200 else White,
                        modifier = Modifier
                            .height(64.dp)
                            .weight(1f)
                            .clickable {
                                viewModel.append("8")
                            }
                    )
                    CalculatorButton(
                        symbol = "9",
                        color = if (darkMode) Black200 else White,
                        textStyle = TextStyle(fontWeight = FontWeight.Bold, color = if (darkMode) White else Black200),
                        modifier = Modifier
                            .height(64.dp)
                            .weight(1f)
                            .clickable {
                                viewModel.append("9")
                            }
                    )
                    CalculatorIconButton(
                        symbol = R.drawable.multiply,
                        color = if (darkMode) Black200 else White,
                        tint = Green,
                        modifier = Modifier
                            .height(64.dp)
                            .weight(1f)
                            .clickable {
                                viewModel.append("×")
                            }
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(
                        symbol = "4",
                        color = if (darkMode) Black200 else White,
                        textStyle = TextStyle(fontWeight = FontWeight.Bold, color = if (darkMode) White else Black200),
                        modifier = Modifier
                            .height(64.dp)
                            .weight(1f)
                            .clickable {
                                viewModel.append("4")
                            }
                    )
                    CalculatorButton(
                        symbol = "5",
                        color = if (darkMode) Black200 else White,
                        textStyle = TextStyle(fontWeight = FontWeight.Bold, color = if (darkMode) White else Black200),
                        modifier = Modifier
                            .height(64.dp)
                            .weight(1f)
                            .clickable {
                                viewModel.append("5")
                            }
                    )
                    CalculatorButton(
                        symbol = "6",
                        color = if (darkMode) Black200 else White,
                        textStyle = TextStyle(fontWeight = FontWeight.Bold, color = if (darkMode) White else Black200),
                        modifier = Modifier
                            .height(64.dp)
                            .weight(1f)
                            .clickable {
                                viewModel.append("6")
                            }
                    )
                    CalculatorIconButton(
                        symbol = R.drawable.minus,
                        color = if (darkMode) Black200 else White,
                        tint = Green,
                        modifier = Modifier
                            .height(64.dp)
                            .weight(1f)
                            .clickable {
                                viewModel.append("-")
                            }
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(
                        symbol = "1",
                        color = if (darkMode) Black200 else White,
                        textStyle = TextStyle(fontWeight = FontWeight.Bold, color = if (darkMode) White else Black200),
                        modifier = Modifier
                            .height(64.dp)
                            .weight(1f)
                            .clickable {
                                viewModel.append("1")
                            }
                    )
                    CalculatorButton(
                        symbol = "2",
                        color = if (darkMode) Black200 else White,
                        textStyle = TextStyle(fontWeight = FontWeight.Bold, color = if (darkMode) White else Black200),
                        modifier = Modifier
                            .height(64.dp)
                            .weight(1f)

                            .clickable {
                                viewModel.append("2")
                            }
                    )
                    CalculatorButton(
                        symbol = "3",
                        color = if (darkMode) Black200 else White,
                        textStyle = TextStyle(fontWeight = FontWeight.Bold, color = if (darkMode) White else Black200),
                        modifier = Modifier
                            .height(64.dp)
                            .weight(1f)
                            .clickable {
                                viewModel.append("3")
                            }
                    )
                    CalculatorIconButton(
                        color = if (darkMode) Black200 else White,
                        symbol = R.drawable.plus,
                        tint = Green,
                        modifier = Modifier
                            .height(64.dp)
                            .weight(1f)
                            .clickable {
                                viewModel.append("+")
                            }
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(
                        symbol = ".",
                        color = if (darkMode) Black200 else White,
                        textStyle = TextStyle(fontWeight = FontWeight.Bold, color = if (darkMode) White else Black200),
                        modifier = Modifier
                            .height(64.dp)
                            .weight(1f)
                            .clickable {
                                viewModel.append(".")
                            }
                    )
                    CalculatorButton(
                        symbol = "0",
                        color = if (darkMode) Black200 else White,
                        textStyle = TextStyle(fontWeight = FontWeight.Bold, color = if (darkMode) White else Black200),
                        modifier = Modifier
                            .height(64.dp)
                            .weight(1f)
                            .clickable {
                                viewModel.append("0")
                            }
                    )

                    CalculatorIconButton(
                        symbol = R.drawable.delete_left_solid,
                        color = if (darkMode) Black200 else White,
                        tint = Red,
                        modifier = Modifier
                            .height(64.dp)
                            .weight(1f)
                            .clickable {
                                viewModel.delete()
                            }
                    )
                    CalculatorIconButton(
                        symbol = R.drawable.equals,
                        color = Green,
                        tint = White,
                        modifier = Modifier
                            .height(64.dp)
                            .weight(1f)
                            .clickable {
                                viewModel.evaluate()
                            }
                    )
                }
            }
        }
    }
}