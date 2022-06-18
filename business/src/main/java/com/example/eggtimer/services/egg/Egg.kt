package com.example.eggtimer.services.egg

enum class EggOrigin(val temperature: Double) {
    FRIDGE(4.0),
    CUPBOARD(21.0)
}

enum class EggVariant(val yolkToWhiteRatio: Double, val desiredTemperature: Double) {
    SOFT(0.6, 65.0),
    WAX(0.86, 65.0),
    HALF(0.98, 65.0),
    HARD(0.86, 77.0),
}

enum class EggSize(val weight: Double) {
    SMALL(47.0),
    MEDIUM(57.0),
    LARGE(67.0)
}

data class Egg(
    val eggVariant: EggVariant = EggVariant.WAX,
    val eggSize: EggSize = EggSize.MEDIUM,
    val eggOrigin: EggOrigin = EggOrigin.FRIDGE,
)