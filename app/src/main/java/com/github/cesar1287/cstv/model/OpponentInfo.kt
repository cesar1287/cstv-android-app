package com.github.cesar1287.cstv.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class OpponentInfo(
    val acronym: String,
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    val name: String
) : Parcelable