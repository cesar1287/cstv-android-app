package com.github.cesar1287.cstv.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.github.cesar1287.cstv.api.PandaScoreApi
import com.github.cesar1287.cstv.model.exception.UnknownErrorException
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.HttpException
import java.io.IOException
import java.lang.ref.WeakReference
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pandaScoreApi: PandaScoreApi
) : ViewModel() {

    interface Delegate {
        fun onUserWithoutInternet()
        fun onApiError()
        fun onUnknownError()
    }

    var delegate: WeakReference<Delegate?> = WeakReference<Delegate?>(null)

    fun handleError(error: Throwable) {
        when(error) {
            is HttpException -> delegate.get()?.onApiError()
            is IOException -> delegate.get()?.onUserWithoutInternet()
            is UnknownErrorException -> delegate.get()?.onUnknownError()
        }
    }

    val flow = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(pageSize = 20)
    ) {
        HomePagingSource(
            pandaScoreApi = pandaScoreApi
        )
    }.flow
        .cachedIn(viewModelScope)
}