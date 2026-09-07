package com.dishtv.remote.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Tv
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dishtv.remote.ui.theme.*

@Composable
fun TopBar(
    isTransmitting: Boolean,
    onMenuClick: () -> Unit,
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
        // IR Blaster Top Emitter LED
        Box(
            modifier = Modifier
                .padding(top = 4.dp, bottom = 8.dp)
                .size(10.dp)
                .shadow(if (isTransmitting) 12.dp else 0.dp, CircleShape, spotColor = DishOrange)
                .clip(CircleShape)
                .background(ledColor)
                .border(1.dp, if (isTransmitting) DishOrange else Color(0xFF2E3648), CircleShape)
        )

        // Main Header Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left Menu Button
            IconButton(
                onClick = onMenuClick,
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = TextPrimary,
                    modifier = Modifier.size(24.dp)
                )
            }

            // Center DishTV Branding
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "dish",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Black,
                        fontStyle = FontStyle.Italic
                    )
                    Text(
                        text = "tv",
                        color = DishOrange,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Black,
                        fontStyle = FontStyle.Italic
                    )
                }
                Text(
                    text = "DishNXT HD",
                    color = TextSecondary,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            // Right Connected Status Indicator
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(contentAlignment = Alignment.TopEnd) {
                    Icon(
                        imageVector = Icons.Default.Tv,
                        contentDescription = "Connected TV",
                        tint = TextPrimary,
                        modifier = Modifier.size(22.dp)
                    )
                    Box(
                        modifier = Modifier
                            .offset(x = 2.dp, y = (-2).dp)
                            .size(7.dp)
                            .clip(CircleShape)
                            .background(ConnectedGreen)
                            .border(1.dp, RemoteBackground, CircleShape)
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Connected",
                    color = TextSecondary,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
