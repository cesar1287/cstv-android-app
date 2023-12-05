package com.github.cesar1287.cstv.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Opponent(
    val opponent: OpponentInfo,
    val type: String
): Parcelable