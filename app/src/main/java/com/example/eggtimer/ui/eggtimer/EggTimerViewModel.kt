package com.example.eggtimer.ui.eggtimer

import androidx.lifecycle.viewModelScope
import com.example.eggtimer.services.egg.EggVariant
import com.example.eggtimer.services.pressure.PressureService
import com.example.eggtimer.ui.eggtimer.usecases.*
import com.example.eggtimer.util.StatefulViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EggTimerViewModel(
    private val pressureService: PressureService,
    private val toggleTimerUseCase: ToggleTimerUseCase,
    private val updateTimerUseCase: UpdateTimerUseCase,
    private val resetTimerUseCase: ResetTimerUseCase,
    private val updatePressureUseCase: UpdatePressureUseCase,
    private val selectEggVariant: SelectEggVariant,
) : StatefulViewModel<EggTimerUIState>(EggTimerUIState()) {

    init {
        viewModelScope.launch {
            pressureService.pressureFlow.collect { pressure ->
                runUseCase(updatePressureUseCase, pressure)
            }
        }
    }

    private var timer: Job? = null

    private fun launchTimer() {
        if (timer?.isActive == true) throw IllegalStateException("Timer launched while another is running")

        timer = viewModelScope.launch {
            delay(1000)
            while (uiState.value.timerRunning) {
                val currentState = uiState.value
                if (currentState.timer <= 0) {
                    runUseCase(toggleTimerUseCase) {
                        cancel()
                    }
                } else {
                    runUseCase(updateTimerUseCase, { it - 1 })
                }
                delay(1000)
            }
        }
    }

    fun selectEggVariant(newVariant: EggVariant) {
        runUseCase(selectEggVariant, newVariant)
    }

    fun toggleTimer() {
        runUseCase(toggleTimerUseCase) {
            if (it.timerRunning) {
                launchTimer()
            } else
                timer?.cancel()
        }
    }

    fun clearTimer() {
        runUseCase(resetTimerUseCase) {
            timer?.cancel()
        }
    }
}
