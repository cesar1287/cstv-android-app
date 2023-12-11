package com.github.cesar1287.cstv.extensions

import android.content.Context
import com.github.cesar1287.cstv.R
import com.github.cesar1287.cstv.model.api.MatchStatus

fun Context.datePretty(status: MatchStatus?, date: String?): String {
    return when(status) {
        MatchStatus.RUNNING -> this.getString(R.string.match_item_live_now_label)
        MatchStatus.FINISHED -> this.getString(R.string.match_item_finished_label)
        MatchStatus.NOT_STARTED,
        MatchStatus.STATUS_NOT_PLAYED -> {
            val matchTime = date?.getPrettyDate()
            if (matchTime?.first == true) {
                this.getString(
                    R.string.match_item_today_match_label,
                    matchTime.second
                )
            }
            else {
                matchTime?.second ?: ""
            }
        }
        else -> ""
    }
}