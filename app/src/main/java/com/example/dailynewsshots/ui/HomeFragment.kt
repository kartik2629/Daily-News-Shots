package com.example.dailynewsshots.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.dailynewsshots.R
import com.example.dailynewsshots.data.remote.APIConstant
import com.example.dailynewsshots.ui.adapter.NewsLoadStateAdapter
import com.example.dailynewsshots.ui.adapter.NewsPagingAdapter
import com.example.dailynewsshots.ui.viewmodel.HomeViewModel
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var pagingAdapter: NewsPagingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // 🔹 Find Views
        val rvNews = view.findViewById<RecyclerView>(R.id.rvNews)
        val shimmerLayout = view.findViewById<ShimmerFrameLayout>(R.id.shimmerLayout)
        val swipeRefresh = view.findViewById<SwipeRefreshLayout>(R.id.swipeRefresh)

        // 🔹 Adapter
        pagingAdapter = NewsPagingAdapter { article ->
            val bundle = bundleOf(
                "title" to article.title,
                "image" to article.urlToImage
            )
            findNavController()
                .navigate(R.id.action_home_to_detail, bundle)
        }

        // 🔹 RecyclerView
        rvNews.layoutManager = LinearLayoutManager(requireContext())
        rvNews.adapter = pagingAdapter.withLoadStateFooter(
            footer = NewsLoadStateAdapter { pagingAdapter.retry() }
        )

        // 🔹 Paging Collect
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.pagedNews(
                APIConstant.DEFAULT_COUNTRY,
                APIConstant.DEFAULT_CATEGORY
            ).collectLatest {
                pagingAdapter.submitData(it)
            }
        }

        // 🔹 Shimmer + LoadState
        pagingAdapter.addLoadStateListener { state ->
            shimmerLayout.isVisible = state.refresh is androidx.paging.LoadState.Loading
            rvNews.isVisible = state.refresh !is androidx.paging.LoadState.Loading
        }

        // 🔹 Swipe Refresh
        swipeRefresh.setOnRefreshListener {
            pagingAdapter.refresh()
            swipeRefresh.isRefreshing = false
        }
        loadBanner(view)
    }

    // 🔹 Toolbar Menu
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_bookmark) {
            findNavController()
                .navigate(R.id.action_home_to_bookmark)
        }
        return true
    }

    private fun loadBanner(view: View) {
        val adView = view.findViewById<AdView>(R.id.bannerAd)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
    }

}
