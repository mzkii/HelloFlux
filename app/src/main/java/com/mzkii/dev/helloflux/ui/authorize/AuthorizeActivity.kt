package com.mzkii.dev.helloflux.ui.authorize

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.mzkii.dev.helloflux.BuildConfig
import com.mzkii.dev.helloflux.R
import com.mzkii.dev.helloflux.databinding.ActivityAuthorizeBinding
import com.mzkii.dev.helloflux.observe
import com.mzkii.dev.helloflux.ui.home.MainActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthorizeActivity : AppCompatActivity() {

    companion object {
        private const val URI = "https://github.com/login/oauth/authorize?client_id=" +
                "${BuildConfig.CLIENT_ID}&scope=gist user admin&redirect_uri=${BuildConfig.REDIRECT_URI}"
    }

    private val store: AuthorizeStore by viewModel()
    private val actionCreator: AuthorizeActionCreator by inject()

    private val binding: ActivityAuthorizeBinding by lazy {
        DataBindingUtil.setContentView<ActivityAuthorizeBinding>(this, R.layout.activity_authorize)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorize)
        initView()
        observeState()
    }

    override fun onResume() {
        super.onResume()
        actionCreator.authorize(intent)
    }

    private fun initView() {
        binding.authorizeButton.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(URI)))
        }
    }

    private fun observeState() {
        store.gotoHomeState.observe(this) {
            finish()
            startActivity(Intent(application, MainActivity::class.java))
        }
    }
}
