package com.github.cesar1287.cstv.features

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
import com.github.cesar1287.cstv.extensions.getPrettyDate
import com.github.cesar1287.cstv.model.MatchStatus
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
            tvMatchTeamB.text = matchVO?.nameTeamB

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

            when(matchVO?.status) {
                MatchStatus.RUNNING -> {
                    tvMatchTime.backgroundTintList = ContextCompat.getColorStateList(
                        itemView.context,
                        R.color.live_now
                    )
                    tvMatchTime.text = itemView.context.getString(R.string.match_item_live_now_label)
                }
                MatchStatus.FINISHED,
                MatchStatus.NOT_STARTED,
                MatchStatus.STATUS_NOT_PLAYED -> {
                    tvMatchTime.backgroundTintList = ContextCompat.getColorStateList(
                        itemView.context,
                        R.color.not_live
                    )
                    val matchTime = matchVO.beginAt?.getPrettyDate()
                    if (matchTime?.first == true) {
                        tvMatchTime.text = itemView.context.getString(
                            R.string.match_item_today_match_label,
                            matchTime.second
                        )
                    }
                    else {
                        tvMatchTime.text = matchTime?.second
                    }
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