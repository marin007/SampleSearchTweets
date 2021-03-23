package com.example.demo_list_tweets.presentation.searchtweets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.viewmodel.ext.android.getViewModel
import com.example.demo_list_tweets.databinding.FragmentSearchTweetsBinding
import com.example.demo_list_tweets.presentation.common.BaseFragment


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
                    }
                }
            }
        })
    }

    override fun subscribeToEvents() {
        viewModel.event.observe(this, { event ->
            event.get()?.let {
                when (it) {
                    is SearchTweetsViewModel.Event.Error -> it.message?.let { it1 ->
                        displayErrorMessage(
                            it1
                        )
                    }
                    is SearchTweetsViewModel.Event.SearchTweets -> viewModel.getTweets(it.keyword)
                }
            }
        })
        viewModel.tweets.observe(this) {

            val adapter = SearchTweetsAdapter(it.statuses, secureContext)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.adapter?.notifyDataSetChanged()
        }
    }

    override fun prepareUi() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.setHasFixedSize(false)
        binding.recyclerView.layoutManager = LinearLayoutManager(secureContext)
    }

    companion object {
        fun newInstance(): SearchTweetsFragment {
            return SearchTweetsFragment()
        }
    }
}