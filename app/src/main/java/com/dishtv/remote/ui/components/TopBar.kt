package com.dishtv.remote.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dishtv.remote.ui.theme.*

@Composable
fun TopBar(
    isTransmitting: Boolean,
    title: String? = null,
    isSecondScreen: Boolean = false,
    onSwipeArrowClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val ledColor by animateColorAsState(
        targetValue = if (isTransmitting) DishOrange else Color(0xFF1F2430),
        animationSpec = tween(durationMillis = 100),
        label = "ir_led_color"
    )

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top IR Blaster Emitter LED
        Box(
            modifier = Modifier
                .padding(top = 4.dp, bottom = 8.dp)
                .size(10.dp)
                .shadow(if (isTransmitting) 12.dp else 0.dp, CircleShape, spotColor = DishOrange)
                .clip(CircleShape)
                .background(ledColor)
                .border(1.dp, if (isTransmitting) DishOrange else Color(0xFF2E3648), CircleShape)
        )

        // Header Action Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left Hamburger Menu (Non-interactive extra features button)
            TactileButton(
                onClick = { /* Reserved for extra app features */ },
                modifier = Modifier.size(40.dp),
                shape = CircleShape,
                backgroundColor = ButtonSurfaceDark.copy(alpha = 0.8f),
                borderColor = ButtonBorderDark
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "App Features",
                    tint = TextSecondary,
                    modifier = Modifier.size(20.dp)
                )
            }

            // Center Title (if present on Screen 2)
            if (title != null) {
                Text(
                    text = title,
                    color = TextPrimary,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.4.sp
                )
            } else {
                Spacer(modifier = Modifier.width(40.dp))
            }

            // Right Swipe Navigation Arrow (Right arrow on Screen 1, Left arrow on Screen 2)
            TactileButton(
                onClick = onSwipeArrowClick,
                modifier = Modifier.size(40.dp),
                shape = CircleShape,
                backgroundColor = ButtonSurfaceDark,
                borderColor = ButtonBorderDark
            ) {
                Icon(
                    imageVector = if (isSecondScreen) Icons.Default.ChevronLeft else Icons.Default.ChevronRight,
                    contentDescription = if (isSecondScreen) "Swipe to Main" else "Swipe to More",
                    tint = TextPrimary,
                    modifier = Modifier.size(22.dp)
                )
            }
        }
    }
}
