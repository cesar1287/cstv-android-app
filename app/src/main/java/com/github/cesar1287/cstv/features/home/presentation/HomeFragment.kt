package com.github.cesar1287.cstv.features.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.cesar1287.cstv.R
import com.github.cesar1287.cstv.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

@AndroidEntryPoint
class HomeFragment : Fragment(), HomeViewModel.Delegate {

    private val viewModel: HomeViewModel by viewModels()

    private val homeAdapter: HomeAdapter by lazy {
        HomeAdapter { onMatchClicked ->
            onMatchClicked?.let {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToMatchDetailFragment(it)
                )
            }
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
        viewModel.delegate = WeakReference(this)

        setupView()

        viewLifecycleOwner.lifecycleScope.launch {
            homeAdapter.loadStateFlow.collectLatest {
                when (val loadState = it.refresh) {
                    is LoadState.Loading -> {
                        with(binding) {
                            pbHomeLoading.isVisible = true
                            rvHomeMatches.isVisible = false
                            tvHomeTitle.isVisible = false
                            tvErrorTitle.isVisible = false
                            btErrorTryAgain.isVisible = false
                        }
                    }

                    is LoadState.NotLoading -> {
                        with(binding) {
                            swRefreshMatches.isRefreshing = false
                            pbHomeLoading.isVisible = false
                            rvHomeMatches.isVisible = true
                            tvHomeTitle.isVisible = true
                            tvErrorTitle.isVisible = false
                            btErrorTryAgain.isVisible = false
                        }
                    }

                    is LoadState.Error -> {
                        viewModel.handleError(loadState.error)
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.matches.collectLatest { pagingData ->
                homeAdapter.submitData(pagingData)
            }
        }
    }

    override fun onUserWithoutInternetError() {
        setupErrorView(
            customMessage = getString(R.string.error_no_connection)
        )
    }

    override fun onApiError() {
        setupErrorView()
    }

    override fun onUnknownError() {
        setupErrorView()
    }

    private fun setupErrorView(
        customMessage: String = getString(R.string.error_default)
    ) {
        with(binding) {
            pbHomeLoading.isVisible = false
            tvHomeTitle.isVisible = false
            rvHomeMatches.isVisible = false
            tvErrorTitle.isVisible = true
            tvErrorTitle.text = customMessage
            btErrorTryAgain.isVisible = true
        }
    }

    private fun setupView() {
        with(binding) {
            rvHomeMatches.apply {
                adapter = homeAdapter
                layoutManager = LinearLayoutManager(context)
            }

            btErrorTryAgain.setOnClickListener {
                homeAdapter.refresh()
            }

            swRefreshMatches.setOnRefreshListener {
                homeAdapter.refresh()
            }
        }
    }
}