package com.example.movieapp

import android.app.Application
import com.example.movieapp.di.appModule
import com.example.movieapp.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieApplication)
            modules(
                listOf(
                    appModule,
                    viewModelsModule
                )
            )
        }
    }
}