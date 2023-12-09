package com.github.cesar1287.cstv.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.cesar1287.cstv.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    private val homeAdapter: HomeAdapter by lazy {
        HomeAdapter { onMatchClicked ->

        }
    }

    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewLifecycleOwner.lifecycleScope.launch {
            homeAdapter.loadStateFlow.collectLatest {
                when(val loadState = it.refresh) {
                    is LoadState.Loading -> {
                        with(binding) {
                            pbHomeLoading.isVisible = true
                            rvHomeMatches.isVisible = false
                            tvHomeTitle.isVisible = false
                        }
                    }
                    is LoadState.NotLoading -> {
                        with(binding) {
                            pbHomeLoading.isVisible = false
                            rvHomeMatches.isVisible = true
                            tvHomeTitle.isVisible = true
                        }
                    }
                    is LoadState.Error -> {
                        viewModel.handleError(loadState.error)
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.flow.collectLatest { pagingData ->
                homeAdapter.submitData(pagingData)
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvHomeMatches.apply {
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}