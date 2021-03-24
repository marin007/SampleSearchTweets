package com.example.demo_list_tweets.presentation.searchtweets

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.viewmodel.ext.android.getViewModel
import com.example.demo_list_tweets.databinding.FragmentSearchTweetsBinding
import com.example.demo_list_tweets.presentation.common.BaseFragment
import com.example.demo_list_tweets.presentation.common.getRecycleViewDivider
import com.example.demo_list_tweets.presentation.common.hideKeyboard


class SearchTweetsFragment : BaseFragment<FragmentSearchTweetsBinding, SearchTweetsViewModel>() {

    override fun viewModel(): SearchTweetsViewModel = getViewModel()

    override fun binding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchTweetsBinding {
        return FragmentSearchTweetsBinding.inflate(inflater, container, false)
    }

    override fun subscribeToUiChanges() {
        viewModel.uiState.observe(this, { state ->
            state.get()?.let {
                when (it) {
                    is SearchTweetsViewModel.UiState.FetchingTweets -> {
                        viewModel.showLoader.set(true)
                    }
                }
            }
        })
    }

    override fun subscribeToEvents() {
        viewModel.event.observe(this, { event ->
            event.get()?.let {
                when (it) {
                    is SearchTweetsViewModel.Event.Error -> it.message?.let { message ->
                        displayErrorMessage(
                            message
                        )
                        viewModel.showLoader.set(false)
                        hideKeyboard(secureContext, view)
                    }
                    is SearchTweetsViewModel.Event.SearchTweets -> viewModel.getTweets(it.keyword)
                }
            }
        })
        viewModel.tweets.observe(this) {
            val adapter = SearchTweetsAdapter(it.statuses, secureContext, ::openTweetInBrowser)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.adapter?.notifyDataSetChanged()
            viewModel.showLoader.set(false)
            hideKeyboard(secureContext, view)
        }
    }

    override fun prepareUi() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.addItemDecoration(getRecycleViewDivider(secureContext))
        binding.recyclerView.setHasFixedSize(false)
        binding.recyclerView.layoutManager = LinearLayoutManager(secureContext)
    }

    private fun openTweetInBrowser (url: String) {
        val i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(i)
    }
}