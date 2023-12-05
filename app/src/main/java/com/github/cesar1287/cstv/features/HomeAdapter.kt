package com.github.cesar1287.cstv.features

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.cesar1287.cstv.databinding.MatchItemBinding
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
            tvMatchTeamB.text = matchVO?.logoTeamB

            vgMatchCardContainer.setOnClickListener {
                onMatchClicked(matchVO)
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