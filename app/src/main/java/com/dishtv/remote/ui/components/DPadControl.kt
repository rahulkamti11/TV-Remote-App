package com.dishtv.remote.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dishtv.remote.ui.theme.*

// 4 Sharp Trapezoid Shapes using standard Compose GenericShape
val UpTrapezoidShape = GenericShape { size, _ ->
    val cornerRadius = 14f
    moveTo(cornerRadius, 0f)
    lineTo(size.width - cornerRadius, 0f)
    quadraticBezierTo(size.width, 0f, size.width - 4f, 8f)
    lineTo(size.width * 0.76f, size.height - 4f)
    quadraticBezierTo(size.width * 0.73f, size.height, size.width * 0.68f, size.height)
    lineTo(size.width * 0.32f, size.height)
    quadraticBezierTo(size.width * 0.27f, size.height, size.width * 0.24f, size.height - 4f)
    lineTo(4f, 8f)
    quadraticBezierTo(0f, 0f, cornerRadius, 0f)
    close()
}

val DownTrapezoidShape = GenericShape { size, _ ->
    val cornerRadius = 14f
    moveTo(size.width * 0.32f, 0f)
    lineTo(size.width * 0.68f, 0f)
    quadraticBezierTo(size.width * 0.73f, 0f, size.width * 0.76f, 4f)
    lineTo(size.width - 4f, size.height - 8f)
    quadraticBezierTo(size.width, size.height, size.width - cornerRadius, size.height)
    lineTo(cornerRadius, size.height)
    quadraticBezierTo(0f, size.height, 4f, size.height - 8f)
    lineTo(size.width * 0.24f, 4f)
    quadraticBezierTo(size.width * 0.27f, 0f, size.width * 0.32f, 0f)
    close()
}

val LeftTrapezoidShape = GenericShape { size, _ ->
    val cornerRadius = 14f
    moveTo(0f, cornerRadius)
    lineTo(0f, size.height - cornerRadius)
    quadraticBezierTo(0f, size.height, 8f, size.height - 4f)
    lineTo(size.width - 4f, size.height * 0.76f)
    quadraticBezierTo(size.width, size.height * 0.73f, size.width, size.height * 0.68f)
    lineTo(size.width, size.height * 0.32f)
    quadraticBezierTo(size.width, size.height * 0.27f, size.width - 4f, size.height * 0.24f)
    lineTo(8f, 4f)
    quadraticBezierTo(0f, 0f, 0f, cornerRadius)
    close()
}

val RightTrapezoidShape = GenericShape { size, _ ->
    val cornerRadius = 14f
    moveTo(0f, size.height * 0.32f)
    lineTo(0f, size.height * 0.68f)
    quadraticBezierTo(0f, size.height * 0.73f, 4f, size.height * 0.76f)
    lineTo(size.width - 8f, size.height - 4f)
    quadraticBezierTo(size.width, size.height, size.width, size.height - cornerRadius)
    lineTo(size.width, cornerRadius)
    quadraticBezierTo(size.width, 0f, size.width - 8f, 4f)
    lineTo(4f, size.height * 0.24f)
    quadraticBezierTo(0f, size.height * 0.27f, 0f, size.height * 0.32f)
    close()
}

@Composable
fun DPadControl(
    onUp: () -> Unit,
    onDown: () -> Unit,
    onLeft: () -> Unit,
    onRight: () -> Unit,
    onOk: () -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 176.dp
) {
    Box(
        modifier = modifier.size(size),
        contentAlignment = Alignment.Center
    ) {
        // UP BUTTON (Top Trapezoid)
        SharpDpadSegment(
            onClick = onUp,
            shape = UpTrapezoidShape,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth(0.92f)
                .height(size * 0.32f)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "Up",
                tint = TextSecondary,
                modifier = Modifier
                    .size(28.dp)
                    .padding(bottom = 6.dp)
            )
        }

        // DOWN BUTTON (Bottom Trapezoid)
        SharpDpadSegment(
            onClick = onDown,
            shape = DownTrapezoidShape,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(0.92f)
                .height(size * 0.32f)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Down",
                tint = TextSecondary,
                modifier = Modifier
                    .size(28.dp)
                    .padding(top = 6.dp)
            )
        }

        // LEFT BUTTON (Left Trapezoid)
        SharpDpadSegment(
            onClick = onLeft,
            shape = LeftTrapezoidShape,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .fillMaxHeight(0.92f)
                .width(size * 0.32f)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "Left",
                tint = TextSecondary,
                modifier = Modifier
                    .size(28.dp)
                    .padding(end = 6.dp)
            )
        }

        // RIGHT BUTTON (Right Trapezoid)
        SharpDpadSegment(
            onClick = onRight,
            shape = RightTrapezoidShape,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .fillMaxHeight(0.92f)
                .width(size * 0.32f)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Right",
                tint = TextSecondary,
                modifier = Modifier
                    .size(28.dp)
                    .padding(start = 6.dp)
            )
        }

        // CENTER OK BUTTON (Rounded Square with clean 10px spacing)
        var isOkPressed by remember { mutableStateOf(false) }
        val okScale by animateFloatAsState(if (isOkPressed) 0.92f else 1f, label = "ok_press")
        val okShape = RoundedCornerShape(14.dp)

        Box(
            modifier = Modifier
                .size(size * 0.33f)
                .semantics { role = Role.Button }
                .scale(okScale)
                .shadow(6.dp, okShape)
                .clip(okShape)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF282F40),
                            Color(0xFF161A24)
                        )
                    )
                )
                .border(1.4.dp, Color(0xFF38435A), okShape)
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
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 0.5.sp
            )
        }
    }
}

@Composable
private fun SharpDpadSegment(
    onClick: () -> Unit,
    shape: Shape,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (isPressed) 0.95f else 1f, label = "segment_press")

    Box(
        modifier = modifier
            .semantics { role = Role.Button }
            .scale(scale)
            .shadow(5.dp, shape)
            .clip(shape)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF242938),
                        Color(0xFF151822)
                    )
                )
            )
            .border(1.2.dp, Color(0xFF2E364A), shape)
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
        contentAlignment = Alignment.Center,
        content = content
    )
}
