package com.dishtv.remote.ir

/**
 * Encodes 32-bit hex command codes into standard NEC protocol pulse-space microsecond intervals.
 * Standard NEC carrier frequency: 38 kHz (38,000 Hz).
 */
object NecEncoder {

    const val CARRIER_FREQUENCY = 38000 // 38 kHz

    private const val LEADING_PULSE = 9000   // 9ms ON
    private const val LEADING_SPACE = 4500   // 4.5ms OFF
    private const val BIT_PULSE = 560        // 560µs ON
    private const val ZERO_SPACE = 560       // 560µs OFF
    private const val ONE_SPACE = 1690       // 1690µs OFF
    private const val STOP_PULSE = 560       // 560µs ON

    /**
     * Converts a 32-bit integer hex code (e.g. 0x10AF8877) to an alternating pulse-space array.
     * NEC protocol transmits 32 bits in LSB-first order (bit 0 to bit 31).
     */
    fun encode(code: Long): IntArray {
        require(code in 0..0xFFFFFFFFL) {
            "NEC code must be a valid 32-bit unsigned value (0x0 to 0xFFFFFFFF), got: 0x${code.toString(16)}"
        }

        // 1 leader pulse + 1 leader space + (32 bits * 2) + 1 stop bit = 67 elements
        val pattern = ArrayList<Int>(67)

        // Leader code (9ms pulse + 4.5ms space)
        pattern.add(LEADING_PULSE)
        pattern.add(LEADING_SPACE)

        // 32-bit payload transmitted LSB-first (Least Significant Bit first)
        for (i in 0 until 32) {
            val bit = (code shr i) and 1L
            pattern.add(BIT_PULSE)
            if (bit == 1L) {
                pattern.add(ONE_SPACE)
            } else {
                pattern.add(ZERO_SPACE)
            }
        }

        // Final Stop Bit (560µs pulse)
        pattern.add(STOP_PULSE)

        return pattern.toIntArray()
    }
}
