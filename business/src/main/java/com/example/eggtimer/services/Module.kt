package com.example.eggtimer.services

import com.example.eggtimer.services.pressure.PressureService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val businessModule = module {
    single { PressureService(androidContext())}
}