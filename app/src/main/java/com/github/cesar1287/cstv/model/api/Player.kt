package com.github.cesar1287.cstv.model.api

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import com.github.cesar1287.cstv.model.vo.PlayerVO
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Player(
    val age: Int,
    val birthday: String?,
    @SerializedName("first_name")
    val firstName: String,
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("last_name")
    val lastName: String,
    val name: String,
    val nationality: String,
    val slug: String
) : Parcelable

fun Player.toUIModel(): PlayerVO {
    return PlayerVO(
        id = this.id,
        fullName = "${this.firstName} ${this.lastName}",
        nickName = this.name,
        imageUrl = this.imageUrl
    )
}