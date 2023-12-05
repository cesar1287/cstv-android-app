package com.github.cesar1287.cstv.features

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.cesar1287.cstv.databinding.MatchItemBinding
import com.github.cesar1287.cstv.model.MatchesResponseItem

class HomeAdapter(
    private val onMatchClicked: (MatchesResponseItem?) -> Unit
) : PagingDataAdapter<MatchesResponseItem, HomeViewHolder>(MatchItemComparator()) {

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

    fun bind(matchVO: MatchesResponseItem?, onMatchClicked: (MatchesResponseItem?) -> Unit) {
        with(binding) {
            tvMatchLeagueSeries.text = matchVO?.name

            tvMatchTeamA.text = matchVO?.name
            tvMatchTeamB.text = matchVO?.name

            vgMatchCardContainer.setOnClickListener {
                onMatchClicked(matchVO)
            }
        }
    }
}

class MatchItemComparator : DiffUtil.ItemCallback<MatchesResponseItem>() {
    override fun areItemsTheSame(oldItem: MatchesResponseItem, newItem: MatchesResponseItem): Boolean {
        // Id is unique.
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MatchesResponseItem, newItem: MatchesResponseItem): Boolean {
        return oldItem == newItem
    }
}