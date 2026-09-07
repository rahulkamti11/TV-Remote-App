package com.dishtv.remote.data

/**
 * Dish TV (DishNXT HD / Standard DTH) 32-bit NEC Protocol Infrared Hex Codes.
 */
object DishTvCodes {
    // Top Row
    const val POWER = 0x10AF8877L
    const val MUTE = 0x10AF48B7L

    // Navigation D-Pad
    const val UP = 0x10AF906FL
    const val DOWN = 0x10AF50AFL
    const val LEFT = 0x10AF10EFL
    const val RIGHT = 0x10AFE01FL
    const val OK = 0x10AFA05FL

    // Rockers
    const val VOL_UP = 0x10AF58A7L
    const val VOL_DOWN = 0x10AF38C7L
    const val CH_UP = 0x10AF00FFL
    const val CH_DOWN = 0x10AF807FL

    // Navigation Actions
    const val BACK = 0x10AF6897L
    const val HOME = 0x10AF7887L

    // Number Pad (0-9) - Unique NEC codes
    const val NUM_0 = 0x10AF08F7L
    const val NUM_1 = 0x10AF827DL
    const val NUM_2 = 0x10AF42BDL
    const val NUM_3 = 0x10AFC837L
    const val NUM_4 = 0x10AF28D7L
    const val NUM_5 = 0x10AF52ADL
    const val NUM_6 = 0x10AF6A95L
    const val NUM_7 = 0x10AFEA15L
    const val NUM_8 = 0x10AF1AE5L
    const val NUM_9 = 0x10AF9867L

    // Screen 2: More Controls
    const val SOURCE = 0x10AF708FL
    const val TV_RADIO = 0x10AFA25DL
    const val GUIDE = 0x10AFB24DL
    const val LANG = 0x10AFD02FL

    const val MY_AC = 0x10AF20DFL
    const val MOD = 0x10AFC03FL
    const val FLIX = 0x10AF40BFL
    const val INFO = 0x10AFB04FL

    const val FAV = 0x10AF30CFL
    const val MY_FILES = 0x10AFB847L
    const val RECORD = 0x10AF32CDL

    // Media Playback
    const val REWIND = 0x10AF629DL
    const val PLAY_PAUSE = 0x10AFE21DL
    const val FORWARD = 0x10AF12EDL
    const val STOP = 0x10AF926DL

    // Color Keys
    const val COLOR_RED = 0x10AF609FL
    const val COLOR_GREEN = 0x10AFA857L
    const val COLOR_YELLOW = 0x10AFE817L
    const val COLOR_BLUE = 0x10AF18E7L
}
