package com.github.cesar1287.cstv.features.matchdetail.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.cesar1287.cstv.R
import com.github.cesar1287.cstv.databinding.TeamAItemBinding
import com.github.cesar1287.cstv.model.vo.PlayerVO

class TeamAAdapter :
    ListAdapter<PlayerVO, TeamAAdapter.TeamAViewHolder>(PlayerVO.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamAViewHolder {
        val binding = TeamAItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamAViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamAViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class TeamAViewHolder(
        private val binding: TeamAItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            player: PlayerVO?
        ) = with(binding) {
            player?.let {
                tvTeamPlayerName.text = player.fullName
                tvTeamPlayerNickname.text = player.nickName
                Glide.with(itemView.context)
                    .load(player.imageUrl)
                    .error(R.drawable.no_logo)
                    .into(ivTeamPlayerFrame)
            }
        }
    }
}