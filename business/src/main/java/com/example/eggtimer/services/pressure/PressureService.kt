package com.example.eggtimer.services.pressure

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

private const val SENSOR_DELAY = SensorManager.SENSOR_DELAY_UI
val PRESSURE_AT_SEALEVEL_HPA = Pressure(1013f)

@JvmInline
value class Pressure(val hPa: Float)

class PressureService(private val context: Context) {
    val pressureFlow = callbackFlow<Pressure> {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val pressureSensor =
            sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE) ?: return@callbackFlow

        val sensorListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                if (event == null || event.sensor?.type != Sensor.TYPE_PRESSURE) return

                val pressure = Pressure(hPa = event.values[0])
                trySend(pressure)
            }

            override fun onAccuracyChanged(p0: Sensor?, p1: Int) = Unit
        }

        sensorManager.registerListener(sensorListener, pressureSensor, SENSOR_DELAY)

        awaitClose {
            sensorManager.unregisterListener(sensorListener)
        }
    }
}
