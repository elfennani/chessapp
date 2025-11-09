package com.elfennani.chess

import android.app.Application
import com.elfennani.chess.di.commonModules
import com.elfennani.chess.di.platformModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(platformModules, commonModules)
        }
    }
}