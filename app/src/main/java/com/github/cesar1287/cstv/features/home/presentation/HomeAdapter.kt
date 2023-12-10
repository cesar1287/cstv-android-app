package com.github.cesar1287.cstv.features.home.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.cesar1287.cstv.R
import com.github.cesar1287.cstv.databinding.MatchItemBinding
import com.github.cesar1287.cstv.extensions.datePretty
import com.github.cesar1287.cstv.model.api.MatchStatus
import com.github.cesar1287.cstv.model.vo.MatchVO

class HomeAdapter(
    private val onMatchClicked: (MatchVO?) -> Unit
) : PagingDataAdapter<MatchVO, HomeViewHolder>(MatchItemComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            MatchItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(getItem(position), onMatchClicked)
    }
}

class HomeViewHolder(
    private val binding: MatchItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        matchVO: MatchVO?,
        onMatchClicked: (MatchVO?) -> Unit
    ) {
        with(binding) {
            tvMatchLeagueSeries.text = matchVO?.nameLeagueSerie

            tvMatchTeamA.text = matchVO?.nameTeamA
                ?: itemView.context.getString(R.string.match_item_tdb_team)
            tvMatchTeamB.text = matchVO?.nameTeamB
                ?: itemView.context.getString(R.string.match_item_tdb_team)

            Glide
                .with(itemView.context)
                .load(matchVO?.logoTeamA)
                .error(R.drawable.no_logo)
                .into(ivMatchTeamA)

            Glide
                .with(itemView.context)
                .load(matchVO?.logoTeamB)
                .error(R.drawable.no_logo)
                .into(ivMatchTeamB)

            Glide
                .with(itemView.context)
                .load(matchVO?.logoLeague)
                .error(R.drawable.no_logo)
                .into(ivMatchLeagueSeries)

            vgMatchCardContainer.setOnClickListener {
                onMatchClicked(matchVO)
            }

            val datePretty = itemView.context.datePretty(matchVO?.status, matchVO?.beginAt)
            tvMatchTime.text = datePretty

            when(matchVO?.status) {
                MatchStatus.RUNNING -> {
                    tvMatchTime.backgroundTintList = ContextCompat.getColorStateList(
                        itemView.context,
                        R.color.live_now
                    )
                }
                MatchStatus.FINISHED,
                MatchStatus.NOT_STARTED,
                MatchStatus.STATUS_NOT_PLAYED -> {
                    tvMatchTime.backgroundTintList = ContextCompat.getColorStateList(
                        itemView.context,
                        R.color.not_live
                    )
                }
                else -> {
                    tvMatchTime.isVisible = false
                }
            }
        }
    }
}

class MatchItemComparator : DiffUtil.ItemCallback<MatchVO>() {
    override fun areItemsTheSame(oldItem: MatchVO, newItem: MatchVO): Boolean {
        // Id is unique.
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MatchVO, newItem: MatchVO): Boolean {
        return oldItem.serieId == newItem.serieId
    }
}