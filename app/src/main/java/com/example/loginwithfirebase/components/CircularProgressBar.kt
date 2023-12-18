package com.example.loginwithfirebase.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginwithfirebase.ui.theme.DarkBlue
import com.example.loginwithfirebase.ui.theme.LightBlue
import com.example.loginwithfirebase.ui.theme.LightGray
import com.example.loginwithfirebase.ui.theme.ghostWhite

@Composable
fun CircularProgressBar(
    modifier: Modifier = Modifier,
    percentage: Float,
    availableMemory: String,
    totalMemory: String,
    number: Int,
    fontSize: TextUnit = 28.sp,
    radius: Dp = 50.dp,
    color: Color = Color.Green,
    strokeWidth: Dp = 4.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0
) {
    var animationPlayed by remember { mutableStateOf(false) }
    val curPercentage = animateFloatAsState(
        targetValue = if (animationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        ),
        label = ""
    )
    val displayTextMemoryUsage = buildAnnotatedString {
        append(availableMemory)
        addStyle(
            style = SpanStyle(
                color = DarkBlue,
            ),
            start = 0,
            end = availableMemory.length
        )
        append(totalMemory)
    }

    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = modifier
                        .size(radius * 2f)
                        .weight(1f)
                ) {
                    Canvas(
                        modifier = modifier.size(radius * 2f)
                    ) {
                        drawArc(
                            color = LightGray,
                            -90f,
                            360f,
                            useCenter = false,
                            style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Square)
                        )
                    }
                    Canvas(
                        modifier = modifier.size(radius * 2f)
                    ) {
                        drawArc(
                            color = DarkBlue,
                            -90f,
                            300 * curPercentage.value,
                            useCenter = false,
                            style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Square)
                        )
                    }
                    Canvas(
                        modifier = modifier.size(radius * 1.75f)
                    ) {
                        drawArc(
                            color = LightBlue,
                            -90f,
                            300 * curPercentage.value,
                            useCenter = false,
                            style = Stroke(width = 8.dp.toPx(), cap = StrokeCap.Square)
                        )
                    }
                    Box(
                        modifier = modifier
                            .background(color = LightBlue, shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = modifier.padding(12.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Used",
                                color = Color.Gray,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = (curPercentage.value * number).toInt().toString() + "%",
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier.weight(1f)
                ) {
                    Text(
                        text = "Mail",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = (curPercentage.value * number).toInt().toString() + "%",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = modifier.height(12.dp))
                    Text(
                        text = displayTextMemoryUsage,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}