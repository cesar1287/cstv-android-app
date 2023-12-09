package com.github.cesar1287.cstv.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.parcelize.Parcelize

@Parcelize
data class Player(
    val age: Int,
    val birthday: String?,
    val first_name: String,
    val id: Int,
    val image_url: String?,
    val last_name: String,
    val modified_at: String,
    val name: String,
    val nationality: String,
    val slug: String
): Parcelable {

    companion object {
        var DIFF_CALLBACK: DiffUtil.ItemCallback<Player> =
            object : DiffUtil.ItemCallback<Player>() {
                override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
                    return oldItem == newItem
                }
            }
    }
}