package com.coderon.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.coderon.calculator.ui.theme.Black500
import com.coderon.calculator.ui.theme.Cyan

@Composable
fun CalculatorButton(
    symbol: String,
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    textStyle: TextStyle = TextStyle(fontWeight = FontWeight.Bold, color = Cyan),
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(100.dp))
            .background(color)
            .then(modifier)
    ) {
        Text(
            text = symbol,
            style = textStyle,
            fontStyle = FontStyle(R.font.public_sans),
            fontSize = 24.sp,
        )
    }
}

@Composable
fun CalculatorIconButton(
    symbol: Int,
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    tint:Color = Cyan
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(100.dp))
            .background(color)
            .then(modifier)
    ) {
        Icon(
            painter = painterResource(id = symbol),
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(24.dp)
        )
    }
}