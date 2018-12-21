package com.mzkii.dev.helloflux.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mzkii.dev.helloflux.R
import com.mzkii.dev.helloflux.observe
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val store: HomeStore by viewModel()
    private val actionCreator: HomeActionCreator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.tag(this::class.java.simpleName).d("hello flux!!")
        store.loadingState.observe(this) {
            Timber.tag(this::class.java.simpleName).d("loadingState = $it")
        }
        actionCreator.load()
    }
}
