package com.dishtv.remote.ir

import android.content.Context
import android.hardware.ConsumerIrManager
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class IrManager(context: Context) {
    private val appContext = context.applicationContext

    private val irManager: ConsumerIrManager? =
        appContext.getSystemService(Context.CONSUMER_IR_SERVICE) as? ConsumerIrManager

    private val vibrator: Vibrator? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val vibratorManager = appContext.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
        vibratorManager?.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        appContext.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
    }

    private val irScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    val hasIr: Boolean = hasIrEmitter()

    private val _isTransmitting = MutableStateFlow(false)
    val isTransmitting: StateFlow<Boolean> = _isTransmitting.asStateFlow()

    private val _lastTransmittedKey = MutableStateFlow("")
    val lastTransmittedKey: StateFlow<String> = _lastTransmittedKey.asStateFlow()

    /**
     * Checks if the device has an IR emitter hardware.
     */
    fun hasIrEmitter(): Boolean {
        return irManager?.hasIrEmitter() == true
    }

    /**
     * Transmits a command code using NEC 38kHz protocol on a background IO thread,
     * triggers haptics, and manages transmission state.
     */
    fun transmit(keyName: String, hexCode: Long) {
        // Immediate haptic feedback
        vibrate()

        _lastTransmittedKey.value = keyName
        _isTransmitting.value = true

        irScope.launch {
            try {
                val pattern = NecEncoder.encode(hexCode)
                if (hasIrEmitter()) {
                    irManager?.transmit(NecEncoder.CARRIER_FREQUENCY, pattern)
                    Log.d("IrManager", "Transmitted $keyName: 0x${hexCode.toString(16).uppercase()}")
                } else {
                    Log.d("IrManager", "Simulated (No IR Hardware) $keyName: 0x${hexCode.toString(16).uppercase()}")
                }
            } catch (e: Exception) {
                Log.e("IrManager", "Error transmitting IR code: ${e.message}", e)
            } finally {
                delay(200)
                _isTransmitting.value = false
            }
        }
    }

    fun stopTransmittingIndicator() {
        _isTransmitting.value = false
    }

    fun release() {
        irScope.cancel()
    }

    private fun vibrate() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator?.vibrate(VibrationEffect.createOneShot(35, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                @Suppress("DEPRECATION")
                vibrator?.vibrate(35)
            }
        } catch (e: Exception) {
            Log.w("IrManager", "Vibration failed: ${e.message}")
        }
    }
}
