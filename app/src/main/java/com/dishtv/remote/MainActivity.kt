package com.dishtv.remote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.dishtv.remote.ir.IrManager
import com.dishtv.remote.ui.screens.RemotePagerScreen
import com.dishtv.remote.ui.theme.DishTvRemoteTheme
import com.dishtv.remote.ui.theme.RemoteBackground

class MainActivity : ComponentActivity() {

    private lateinit var irManager: IrManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        irManager = IrManager(this)

        setContent {
            DishTvRemoteTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = RemoteBackground
                ) {
                    RemotePagerScreen(irManager = irManager)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        irManager.release()
    }
}
