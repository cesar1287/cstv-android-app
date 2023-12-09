package com.github.cesar1287.cstv.model.vo

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlayerVO(
    val id: Int,
    val imageUrl: String?,
    val fullName: String?,
    val nickName: String?
) : Parcelable {

    companion object {
        var DIFF_CALLBACK: DiffUtil.ItemCallback<PlayerVO> =
            object : DiffUtil.ItemCallback<PlayerVO>() {
                override fun areItemsTheSame(oldItem: PlayerVO, newItem: PlayerVO): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: PlayerVO, newItem: PlayerVO): Boolean {
                    return oldItem.imageUrl == newItem.imageUrl
                }
            }
    }
}