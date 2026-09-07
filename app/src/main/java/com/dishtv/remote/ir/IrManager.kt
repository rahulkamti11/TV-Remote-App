package com.dishtv.remote.ir

import android.content.Context
import android.hardware.ConsumerIrManager
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class IrManager(private val context: Context) {

    private val irManager: ConsumerIrManager? =
        context.getSystemService(Context.CONSUMER_IR_SERVICE) as? ConsumerIrManager

    private val vibrator: Vibrator? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
        vibratorManager?.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
    }

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
     * Transmits a command code using NEC 38kHz protocol, triggers haptics, and updates UI state.
     */
    fun transmit(keyName: String, hexCode: Long) {
        // Trigger haptic vibration
        vibrate()

        _lastTransmittedKey.value = keyName
        _isTransmitting.value = true

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
        }
    }

    fun stopTransmittingIndicator() {
        _isTransmitting.value = false
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
