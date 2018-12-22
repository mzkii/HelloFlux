package com.mzkii.dev.helloflux.ui.home

import android.content.Intent
import android.net.Uri
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
import com.mzkii.dev.helloflux.ui.FetchMoreScrollListener
import com.mzkii.dev.helloflux.ui.authorize.AuthorizeActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val store: HomeStore by viewModel()
    private val actionCreator: HomeActionCreator by inject()

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    private val repositoryAdapter: RepositoryAdapter by lazy {
        RepositoryAdapter(
            onCardClick = { repository ->
                repository.url?.let { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it))) }
            }
        )
    }

    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        observeState()
        isLoading = true
        actionCreator.getMyRepositoryList(1)
    }

    private fun initView() {
        title = "My repositories"
        with(binding.recyclerView) {
            adapter = repositoryAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            addOnScrollListener(object : FetchMoreScrollListener() {
                override fun onLoadMore() {
                    if (!isLoading && store.canFetchMore) {
                        isLoading = true
                        actionCreator.getMyRepositoryList(store.pageNum)
                    }
                }
            })
        }
    }

    private fun observeState() {
        store.loadingState.observe(this) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
            isLoading = it
        }
        store.loadedRepositoryListState.observe(this) {
            repositoryAdapter.submitList(it.toList())
        }
    }
}
