package com.github.cesar1287.cstv.model.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Serie(
    @SerializedName("full_name")
    val fullName: String,
    val id: Int,
): Parcelable