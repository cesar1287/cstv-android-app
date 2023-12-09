package com.github.cesar1287.cstv.features.matchdetail.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.cesar1287.cstv.R
import com.github.cesar1287.cstv.databinding.TeamAItemBinding
import com.github.cesar1287.cstv.model.Player

class TeamAAdapter :
    ListAdapter<Player, TeamAAdapter.TeamAViewHolder>(Player.DIFF_CALLBACK) {

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
            player: Player?
        ) = with(binding) {
            player?.let {
                tvTeamPlayerName.text = player.first_name
                tvTeamPlayerNickname.text = player.name
                Glide.with(itemView.context)
                    .load(player.image_url)
                    .error(R.drawable.no_logo)
                    .into(ivTeamPlayerFrame)
            }
        }
    }
}