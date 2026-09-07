package com.dishtv.remote.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dishtv.remote.ui.theme.*

@Composable
fun DPadControl(
    onUp: () -> Unit,
    onDown: () -> Unit,
    onLeft: () -> Unit,
    onRight: () -> Unit,
    onOk: () -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 190.dp
) {
    Box(
        modifier = modifier
            .size(size)
            .shadow(10.dp, CircleShape)
            .clip(CircleShape)
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xFF1E2330),
                        Color(0xFF13161E)
                    )
                )
            )
            .border(1.5.dp, Color(0xFF2B3345), CircleShape),
        contentAlignment = Alignment.Center
    ) {
        // UP Button
        DPadArrowButton(
            onClick = onUp,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 10.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowDropUp,
                contentDescription = "Up",
                tint = TextPrimary,
                modifier = Modifier.size(36.dp)
            )
        }

        // DOWN Button
        DPadArrowButton(
            onClick = onDown,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 10.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Down",
                tint = TextPrimary,
                modifier = Modifier.size(36.dp)
            )
        }

        // LEFT Button
        DPadArrowButton(
            onClick = onLeft,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 10.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowLeft,
                contentDescription = "Left",
                tint = TextPrimary,
                modifier = Modifier.size(36.dp)
            )
        }

        // RIGHT Button
        DPadArrowButton(
            onClick = onRight,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 10.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowRight,
                contentDescription = "Right",
                tint = TextPrimary,
                modifier = Modifier.size(36.dp)
            )
        }

        // Center OK Button
        var isOkPressed by remember { mutableStateOf(false) }
        val okScale by animateFloatAsState(if (isOkPressed) 0.92f else 1f, label = "ok_scale")

        Box(
            modifier = Modifier
                .size(70.dp)
                .scale(okScale)
                .shadow(8.dp, CircleShape)
                .clip(CircleShape)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color(0xFF262C3D),
                            Color(0xFF161922)
                        )
                    )
                )
                .border(1.5.dp, Color(0xFF3B4660), CircleShape)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = {
                            isOkPressed = true
                            tryAwaitRelease()
                            isOkPressed = false
                        },
                        onTap = { onOk() }
                    )
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "OK",
                color = TextPrimary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun DPadArrowButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (isPressed) 0.86f else 1f, label = "arrow_scale")

    Box(
        modifier = modifier
            .size(44.dp)
            .scale(scale)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        isPressed = true
                        tryAwaitRelease()
                        isPressed = false
                    },
                    onTap = { onClick() }
                )
            },
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}
