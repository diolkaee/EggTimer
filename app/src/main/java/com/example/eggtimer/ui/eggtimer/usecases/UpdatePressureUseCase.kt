package com.example.eggtimer.ui.eggtimer.usecases

import com.example.eggtimer.services.pressure.Pressure
import com.example.eggtimer.ui.eggtimer.EggTimerUIState
import com.example.eggtimer.util.StateAction
import com.example.eggtimer.util.UseCase
import com.example.eggtimer.util.UseCaseAction
import kotlin.math.abs

class UpdatePressureUseCase : UseCase<EggTimerUIState, Pressure> {
    override suspend fun invoke(
        state: EggTimerUIState,
        parameter: Pressure
    ): UseCaseAction<EggTimerUIState> {
        return StateAction {
            val isSignificant = abs(pressure - state.pressure) > 1f
            if (isSignificant) {
                if (timerRunning) {
                    copy(pressure = pressure)
                }
                else {
                    copy(pressure = pressure).resetTimer()
                }
            } else this
        }
    }
}