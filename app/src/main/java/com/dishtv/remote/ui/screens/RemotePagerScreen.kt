package com.dishtv.remote.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dishtv.remote.ir.IrManager
import com.dishtv.remote.ui.components.TopBar
import com.dishtv.remote.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RemotePagerScreen(
    irManager: IrManager,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { 2 })
    val coroutineScope = rememberCoroutineScope()

    val isTransmitting by irManager.isTransmitting.collectAsState()
    val lastKey by irManager.lastTransmittedKey.collectAsState()

    var showToast by remember { mutableStateOf(false) }

    // Auto-reset IR LED flash after 200ms
    LaunchedEffect(isTransmitting) {
        if (isTransmitting) {
            showToast = true
            delay(200)
            irManager.stopTransmittingIndicator()
            delay(1000)
            showToast = false
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(RemoteBackground)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top Bar (shown on Screen 1)
            if (pagerState.currentPage == 0) {
                TopBar(
                    isTransmitting = isTransmitting,
                    onMenuClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(1)
                        }
                    }
                )
            } else {
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Horizontal Swipeable Pager
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) { page ->
                when (page) {
                    0 -> MainRemoteScreen(irManager = irManager)
                    1 -> MoreControlsScreen(
                        irManager = irManager,
                        onBackToMain = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(0)
                            }
                        }
                    )
                }
            }

            // Bottom Paging Indicator Dots & Gesture Bar
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // Indicator Dots
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(2) { pageIndex ->
                        val isSelected = pagerState.currentPage == pageIndex
                        val width by animateDpAsState(
                            targetValue = if (isSelected) 24.dp else 8.dp,
                            label = "dot_width"
                        )
                        Box(
                            modifier = Modifier
                                .height(4.dp)
                                .width(width)
                                .clip(CircleShape)
                                .background(if (isSelected) DishOrange else Color(0xFF333D50))
                                .clickable {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(pageIndex)
                                    }
                                }
                        )
                    }
                }

                // Gesture navigation pill indicator
                Box(
                    modifier = Modifier
                        .width(110.dp)
                        .height(3.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(Color(0xFF3E4759))
                )
            }
        }

        // Animated Toast Banner for Transmitted Key Feedback
        AnimatedVisibility(
            visible = showToast && lastKey.isNotEmpty(),
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 36.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = Color(0xFF1E2430),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF374156)),
                shadowElevation = 8.dp
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Transmitting: ",
                        color = TextSecondary,
                        fontSize = 12.sp
                    )
                    Text(
                        text = lastKey,
                        color = DishOrange,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}
