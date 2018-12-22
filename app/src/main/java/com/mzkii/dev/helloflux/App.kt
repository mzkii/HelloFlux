package com.mzkii.dev.helloflux

import android.app.Application
import com.mzkii.dev.flux.Dispatcher
import com.mzkii.dev.helloflux.ui.home.HomeActionCreator
import com.mzkii.dev.helloflux.ui.home.HomeStore
import org.koin.android.ext.android.startKoin
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import timber.log.Timber

class App : Application() {

    private val appModule = module {
        single { Dispatcher() }
    }

    private val uiModule = module {
        factory { HomeActionCreator(get()) }
        viewModel { HomeStore(get()) }
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin(this, listOf(appModule, uiModule))
    }
}