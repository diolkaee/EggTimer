package com.example.eggtimer

import com.example.eggtimer.services.businessModule
import com.example.eggtimer.ui.eggtimer.EggTimerViewModel
import com.example.eggtimer.ui.eggtimer.usecases.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = businessModule + module {
    // UseCases
    single { ToggleTimerUseCase() }
    single { UpdateTimerUseCase() }
    single { ResetTimerUseCase() }
    single { UpdatePressureUseCase() }
    single { SelectEggVariant() }

    // ViewModels
    viewModel { EggTimerViewModel(get(), get(), get(), get(), get(), get()) }
}