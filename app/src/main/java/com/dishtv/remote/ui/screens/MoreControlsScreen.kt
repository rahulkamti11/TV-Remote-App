package com.dishtv.remote.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dishtv.remote.data.DishTvCodes
import com.dishtv.remote.ir.IrManager
import com.dishtv.remote.ui.components.TactileButton
import com.dishtv.remote.ui.theme.*

@Composable
fun MoreControlsScreen(
    irManager: IrManager,
    onBackToMain: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // 1. Top Header with Back Arrow, Title, and DishTV Branding
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = onBackToMain,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .size(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back to Main",
                        tint = TextPrimary
                    )
                }

                Text(
                    text = "More Controls",
                    style = Typography.titleLarge,
                    fontSize = 18.sp
                )
            }

            // DishTV Branding Subtitle
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 2.dp)
            ) {
                Text(
                    text = "dish",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Black,
                    fontStyle = FontStyle.Italic
                )
                Text(
                    text = "tv",
                    color = DishOrange,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Black,
                    fontStyle = FontStyle.Italic
                )
            }
            Text(
                text = "DishNXT HD",
                color = TextSecondary,
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium
            )
        }

        // 2. 4x5 Function Grid
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Row 1: Source, TV/Radio, Guide, LANG
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                GridActionTile(
                    label = "Source",
                    icon = Icons.Default.Input,
                    onClick = { irManager.transmit("SOURCE", DishTvCodes.SOURCE) },
                    modifier = Modifier.weight(1f)
                )
                GridActionTile(
                    label = "TV/Radio",
                    icon = Icons.Default.Tv,
                    onClick = { irManager.transmit("TV/RADIO", DishTvCodes.TV_RADIO) },
                    modifier = Modifier.weight(1f)
                )
                GridActionTile(
                    label = "Guide",
                    icon = Icons.Default.FormatListBulleted,
                    onClick = { irManager.transmit("GUIDE", DishTvCodes.GUIDE) },
                    modifier = Modifier.weight(1f)
                )
                GridCustomTextTile(
                    mainText = "LANG",
                    subText = "Language",
                    onClick = { irManager.transmit("LANG", DishTvCodes.LANG) },
                    modifier = Modifier.weight(1f)
                )
            }

            // Row 2: My A/C, MOD, FLIX, Info
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                GridActionTile(
                    label = "My A/C",
                    icon = Icons.Default.AccountBox,
                    onClick = { irManager.transmit("MY A/C", DishTvCodes.MY_AC) },
                    modifier = Modifier.weight(1f)
                )
                GridActionTile(
                    label = "MOD",
                    icon = Icons.Default.GridView,
                    onClick = { irManager.transmit("MOD", DishTvCodes.MOD) },
                    modifier = Modifier.weight(1f)
                )
                GridActionTile(
                    label = "FLIX",
                    icon = Icons.Default.Movie,
                    onClick = { irManager.transmit("FLIX", DishTvCodes.FLIX) },
                    modifier = Modifier.weight(1f)
                )
                GridActionTile(
                    label = "Info",
                    icon = Icons.Default.Info,
                    onClick = { irManager.transmit("INFO", DishTvCodes.INFO) },
                    modifier = Modifier.weight(1f)
                )
            }

            // Row 3: Home, FAV, My Files, Record
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                GridActionTile(
                    label = "Home",
                    icon = Icons.Default.Home,
                    onClick = { irManager.transmit("HOME", DishTvCodes.HOME) },
                    modifier = Modifier.weight(1f)
                )
                GridActionTile(
                    label = "FAV",
                    icon = Icons.Default.Favorite,
                    onClick = { irManager.transmit("FAV", DishTvCodes.FAV) },
                    modifier = Modifier.weight(1f)
                )
                GridActionTile(
                    label = "My Files",
                    icon = Icons.Default.Folder,
                    onClick = { irManager.transmit("MY FILES", DishTvCodes.MY_FILES) },
                    modifier = Modifier.weight(1f)
                )
                GridDotTile(
                    label = "Record",
                    dotColor = Color(0xFFEF4444),
                    onClick = { irManager.transmit("RECORD", DishTvCodes.RECORD) },
                    modifier = Modifier.weight(1f)
                )
            }

            // Row 4: Media Controls (Rewind, Play/Pause, Forward, Stop)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                GridActionTile(
                    label = "Rewind",
                    icon = Icons.Default.FastRewind,
                    onClick = { irManager.transmit("REWIND", DishTvCodes.REWIND) },
                    modifier = Modifier.weight(1f)
                )
                GridActionTile(
                    label = "Play/Pause",
                    icon = Icons.Default.PlayArrow,
                    onClick = { irManager.transmit("PLAY/PAUSE", DishTvCodes.PLAY_PAUSE) },
                    modifier = Modifier.weight(1f)
                )
                GridActionTile(
                    label = "Forward",
                    icon = Icons.Default.FastForward,
                    onClick = { irManager.transmit("FORWARD", DishTvCodes.FORWARD) },
                    modifier = Modifier.weight(1f)
                )
                GridActionTile(
                    label = "Stop",
                    icon = Icons.Default.Stop,
                    onClick = { irManager.transmit("STOP", DishTvCodes.STOP) },
                    modifier = Modifier.weight(1f)
                )
            }

            // Row 5: 4 Color Keys (Red, Green, Yellow, Blue)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                GridDotTile(
                    label = "Red",
                    dotColor = ColorKeyRed,
                    onClick = { irManager.transmit("RED", DishTvCodes.COLOR_RED) },
                    modifier = Modifier.weight(1f)
                )
                GridDotTile(
                    label = "Green",
                    dotColor = ColorKeyGreen,
                    onClick = { irManager.transmit("GREEN", DishTvCodes.COLOR_GREEN) },
                    modifier = Modifier.weight(1f)
                )
                GridDotTile(
                    label = "Yellow",
                    dotColor = ColorKeyYellow,
                    onClick = { irManager.transmit("YELLOW", DishTvCodes.COLOR_YELLOW) },
                    modifier = Modifier.weight(1f)
                )
                GridDotTile(
                    label = "Blue",
                    dotColor = ColorKeyBlue,
                    onClick = { irManager.transmit("BLUE", DishTvCodes.COLOR_BLUE) },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // 3. Bottom IR Positioning Hint Card
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFF161922))
                .border(1.dp, Color(0xFF262C3A), RoundedCornerShape(16.dp))
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = null,
                tint = TextSecondary,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Point your phone's IR blaster towards your DishTV set-top box (DishNXT HD).",
                color = TextSecondary,
                fontSize = 11.sp,
                lineHeight = 15.sp
            )
        }
    }
}

@Composable
private fun GridActionTile(
    label: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TactileButton(
        onClick = onClick,
        modifier = modifier.height(56.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = TextPrimary,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = label,
                color = TextSecondary,
                fontSize = 9.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun GridCustomTextTile(
    mainText: String,
    subText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TactileButton(
        onClick = onClick,
        modifier = modifier.height(56.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = mainText,
                color = TextPrimary,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = subText,
                color = TextSecondary,
                fontSize = 8.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Composable
private fun GridDotTile(
    label: String,
    dotColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TactileButton(
        onClick = onClick,
        modifier = modifier.height(56.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(13.dp)
                    .clip(CircleShape)
                    .background(dotColor)
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = label,
                color = TextSecondary,
                fontSize = 9.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
