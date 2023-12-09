package com.github.cesar1287.cstv.features.matchdetail.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.github.cesar1287.cstv.R
import com.github.cesar1287.cstv.databinding.FragmentMatchDetailBinding
import com.github.cesar1287.cstv.extensions.datePretty
import com.github.cesar1287.cstv.model.vo.MatchVO
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchDetailFragment : Fragment() {

    private val binding: FragmentMatchDetailBinding by lazy {
        FragmentMatchDetailBinding.inflate(layoutInflater)
    }

    private val args by navArgs<MatchDetailFragmentArgs>()

    private val match: MatchVO by lazy {
        args.match
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        with(binding) {
            tvMatchDetailLeagueSeries.text = match.nameLeagueSerie
            tvMatchDetailHour.text = context?.datePretty(match.status, match.beginAt)
            tvMatchTeamA.text = match.nameTeamA
            tvMatchTeamB.text = match.nameTeamB

            context?.let { contextNonNull ->
                Glide.with(contextNonNull)
                    .load(match.logoTeamA)
                    .error(R.drawable.no_logo)
                    .into(ivMatchTeamA)

                Glide.with(contextNonNull)
                    .load(match.logoTeamB)
                    .error(R.drawable.no_logo)
                    .into(ivMatchTeamB)
            }

            ivMatchDetailBack.setOnClickListener {
                findNavController().popBackStack()
            }

//            rvMatchDetailTeamA.apply {
//                adapter = teamAAdapter
//                layoutManager = LinearLayoutManager(context)
//            }

//            rvMatchDetailTeamB.apply {
//                adapter = teamBAdapter
//                layoutManager = LinearLayoutManager(context)
//            }

            btMatchDetailErrorPlayers.setOnClickListener {
                //matchDetailViewModel.getTeams(match.teamAId, match.teamBId)
                gpErrorContent.isVisible = false
                pbMatchDetailPlayer.isVisible = true
                rvMatchDetailTeamA.isVisible = false
                rvMatchDetailTeamB.isVisible = false
            }
        }
    }
}