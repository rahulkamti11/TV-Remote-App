package com.dishtv.remote.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PowerSettingsNew
import androidx.compose.material.icons.filled.Undo
import androidx.compose.material.icons.filled.VolumeOff
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dishtv.remote.data.DishTvCodes
import com.dishtv.remote.ir.IrManager
import com.dishtv.remote.ui.components.*
import com.dishtv.remote.ui.theme.*

@Composable
fun MainRemoteScreen(
    irManager: IrManager,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // 1. Power & Mute Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Power Button (Red)
            CircularTactileButton(
                onClick = { irManager.transmit("POWER", DishTvCodes.POWER) },
                size = 54.dp,
                backgroundColor = Color(0xFFDC2626),
                borderColor = Color(0xFFEF4444),
                label = "Power"
            ) {
                Icon(
                    imageVector = Icons.Default.PowerSettingsNew,
                    contentDescription = "Power",
                    tint = Color.White,
                    modifier = Modifier.size(26.dp)
                )
            }

            // Mute Button (Dark)
            CircularTactileButton(
                onClick = { irManager.transmit("MUTE", DishTvCodes.MUTE) },
                size = 54.dp,
                label = "Mute"
            ) {
                Icon(
                    imageVector = Icons.Default.VolumeOff,
                    contentDescription = "Mute",
                    tint = TextPrimary,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        // 2. Middle Controls: VOL Rocker + D-Pad + CH Rocker
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Volume Rocker
            RockerControl(
                label = "VOL",
                onPlus = { irManager.transmit("VOL +", DishTvCodes.VOL_UP) },
                onMinus = { irManager.transmit("VOL -", DishTvCodes.VOL_DOWN) }
            )

            // Center Circular D-PAD
            DPadControl(
                onUp = { irManager.transmit("UP", DishTvCodes.UP) },
                onDown = { irManager.transmit("DOWN", DishTvCodes.DOWN) },
                onLeft = { irManager.transmit("LEFT", DishTvCodes.LEFT) },
                onRight = { irManager.transmit("RIGHT", DishTvCodes.RIGHT) },
                onOk = { irManager.transmit("OK", DishTvCodes.OK) },
                size = 180.dp
            )

            // Channel Rocker
            RockerControl(
                label = "CH",
                onPlus = { irManager.transmit("CH +", DishTvCodes.CH_UP) },
                onMinus = { irManager.transmit("CH -", DishTvCodes.CH_DOWN) }
            )
        }

        // 3. Back & Home Buttons Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 2.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Back Button
            CircularTactileButton(
                onClick = { irManager.transmit("BACK", DishTvCodes.BACK) },
                size = 48.dp,
                label = "Back"
            ) {
                Icon(
                    imageVector = Icons.Default.Undo,
                    contentDescription = "Back",
                    tint = TextPrimary,
                    modifier = Modifier.size(22.dp)
                )
            }

            // Home Button
            CircularTactileButton(
                onClick = { irManager.transmit("HOME", DishTvCodes.HOME) },
                size = 48.dp,
                label = "Home"
            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = TextPrimary,
                    modifier = Modifier.size(22.dp)
                )
            }
        }

        // 4. Number Pad (3x4 pill layout)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val numpadRows = listOf(
                listOf("1" to DishTvCodes.NUM_1, "2" to DishTvCodes.NUM_2, "3" to DishTvCodes.NUM_3),
                listOf("4" to DishTvCodes.NUM_4, "5" to DishTvCodes.NUM_5, "6" to DishTvCodes.NUM_6),
                listOf("7" to DishTvCodes.NUM_7, "8" to DishTvCodes.NUM_8, "9" to DishTvCodes.NUM_9)
            )

            numpadRows.forEach { row ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    row.forEach { (digit, code) ->
                        TactileButton(
                            onClick = { irManager.transmit(digit, code) },
                            modifier = Modifier
                                .weight(1f)
                                .height(44.dp),
                            shape = RoundedCornerShape(20.dp)
                        ) {
                            Text(
                                text = digit,
                                color = TextPrimary,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }

            // Bottom Centered "0" Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Spacer(modifier = Modifier.weight(1f))
                TactileButton(
                    onClick = { irManager.transmit("0", DishTvCodes.NUM_0) },
                    modifier = Modifier
                        .weight(1f)
                        .height(44.dp),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(
                        text = "0",
                        color = TextPrimary,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}
