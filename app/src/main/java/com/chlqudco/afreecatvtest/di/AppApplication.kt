package com.chlqudco.afreecatvtest.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AppApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        //Koin 연결
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@AppApplication)
            modules(appModule)
        }
    }
}