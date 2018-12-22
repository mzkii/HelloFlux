package com.mzkii.dev.helloflux

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.mzkii.dev.flux.Dispatcher
import com.mzkii.dev.githubgistclient.data.preferences.AppSetting
import com.mzkii.dev.helloflux.data.api.AuthorizeApi
import com.mzkii.dev.helloflux.data.api.GithubApi
import com.mzkii.dev.helloflux.data.repository.AuthorizeRepository
import com.mzkii.dev.helloflux.data.repository.GithubRepository
import com.mzkii.dev.helloflux.ui.authorize.AuthorizeActionCreator
import com.mzkii.dev.helloflux.ui.authorize.AuthorizeStore
import com.mzkii.dev.helloflux.ui.home.HomeActionCreator
import com.mzkii.dev.helloflux.ui.home.HomeStore
import okhttp3.OkHttpClient
import org.koin.android.ext.android.startKoin
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber

class App : Application() {

    companion object {
        private const val PREF_NAME = "PrefName"
    }

    private val appModule = module {
        single { Dispatcher() }
        single { AppSetting(getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)!!) }
    }

    private val networkModule = module {
        single {
            Retrofit
                .Builder()
                .client(OkHttpClient.Builder().build())
                .baseUrl("https://github.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(AuthorizeApi::class.java)
        }
        single {
            Retrofit
                .Builder()
                .client(OkHttpClient.Builder().build())
                .baseUrl("https://api.github.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(GithubApi::class.java)
        }
        single { AuthorizeRepository(get()) }
        single { GithubRepository(get()) }

    }

    private val uiModule = module {
        factory { HomeActionCreator(get(), get(), get()) }
        viewModel { HomeStore(get()) }

        factory { AuthorizeActionCreator(get(), get()) }
        viewModel { AuthorizeStore(get(), get()) }
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin(this, listOf(appModule, uiModule, networkModule))
    }
}