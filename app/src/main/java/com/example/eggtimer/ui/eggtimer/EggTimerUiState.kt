package com.example.eggtimer.ui.eggtimer

import com.example.eggtimer.services.egg.EggOrigin
import com.example.eggtimer.services.egg.EggSize
import com.example.eggtimer.services.egg.EggVariant
import com.example.eggtimer.services.egg.calculateBoilingTime
import com.example.eggtimer.services.pressure.PRESSURE_AT_SEALEVEL_HPA
import com.example.eggtimer.services.pressure.Pressure
import com.example.eggtimer.util.UiState


data class EggTimerUIState(
    val eggVariant: EggVariant = EggVariant.WAX,
    val eggSize: EggSize = EggSize.MEDIUM,
    val eggOrigin: EggOrigin = EggOrigin.FRIDGE,
    val timerRunning: Boolean = false,
    val pressure: Pressure = PRESSURE_AT_SEALEVEL_HPA,
    val timer: Int = calculateBoilingTime(
        weight = eggSize.weight,
        yolkToWhiteRatio = eggVariant.yolkToWhiteRatio,
        eggTemperature = eggOrigin.temperature,
        desiredTemperature = eggVariant.desiredTemperature,
        pressure = pressure
    )
) : UiState {
    fun resetTimer() = this.copy(timerRunning = false, timer = boilingTime())

    private fun boilingTime() = calculateBoilingTime(
        weight = eggSize.weight,
        yolkToWhiteRatio = eggVariant.yolkToWhiteRatio,
        eggTemperature = eggOrigin.temperature,
        desiredTemperature = eggVariant.desiredTemperature,
        pressure = pressure
    )
}
