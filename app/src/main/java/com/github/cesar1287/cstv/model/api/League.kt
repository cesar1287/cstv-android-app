package com.github.cesar1287.cstv.model.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class League(
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    val name: String,
): Parcelable