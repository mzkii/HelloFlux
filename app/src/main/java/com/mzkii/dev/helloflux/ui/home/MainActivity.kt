package com.mzkii.dev.helloflux.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mzkii.dev.githubgistclient.data.preferences.AppSetting
import com.mzkii.dev.helloflux.R
import com.mzkii.dev.helloflux.databinding.ActivityMainBinding
import com.mzkii.dev.helloflux.observe
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val store: HomeStore by viewModel()
    private val actionCreator: HomeActionCreator by inject()
    private val appSetting: AppSetting by inject()

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    private val repositoryAdapter: RepositoryAdapter by lazy {
        RepositoryAdapter(
            onCardClick = {
                Timber.tag(this::class.java.simpleName).d("clicked repository = $it")
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        observeState()
        actionCreator.getMyRepositoryList()
    }

    private fun initView() {
        Timber.tag(this::class.java.simpleName).d("token = ${appSetting.accessToken}")
        title = "My repositories"
        binding.recyclerView.adapter = repositoryAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    private fun observeState() {
        store.loadingState.observe(this) {
            Timber.tag(this::class.java.simpleName).d("loadingState = $it")
            binding.progressBar.visibility = if(it) View.VISIBLE else View.GONE
        }
        store.loadedRepositoryListState.observe(this) {
            Timber.tag(this::class.java.simpleName).d("loadedRepositoryListState = $it")

            repositoryAdapter.submitList(it.toList())

            Timber.tag(this::class.java.simpleName).d("repositoryAdapter = ${repositoryAdapter.itemCount}")
            binding.recyclerView.visibility = View.VISIBLE
        }
    }
}
