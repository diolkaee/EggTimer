package com.example.eggtimer.services.egg

import com.example.eggtimer.services.pressure.Pressure
import kotlin.math.ln
import kotlin.math.pow
import kotlin.math.roundToInt

const val heatCoefficient = 31
const val hPaToInHg = 0.02953

// Taken from https://www.omnicalculator.com/chemistry/boiling-point-altitude
private fun calculateBoilingPoint(pressureHPa: Float) =
    ((49.161 * ln(pressureHPa * hPaToInHg) + 44.932) - 32.0) * (5.0 / 9.0)

//Taken from https://www.omnicalculator.com/food/egg-boiling#the-science-behind-the-boiling-of-an-egg
fun calculateBoilingTime(
    weight: Double,
    yolkToWhiteRatio: Double,
    eggTemperature: Double,
    desiredTemperature: Double,
    pressure: Pressure,
) = (weight.pow(2.0 / 3.0) *
        heatCoefficient *
        ln(
            yolkToWhiteRatio *
                    (eggTemperature - calculateBoilingPoint(pressure.hPa)) /
                    (desiredTemperature - calculateBoilingPoint(pressure.hPa))
        ) +
        10
        ).roundToInt()