package com.github.cesar1287.cstv.features.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.github.cesar1287.cstv.features.home.domain.HomeUseCase
import com.github.cesar1287.cstv.model.exception.UnknownErrorException
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.HttpException
import java.io.IOException
import java.lang.ref.WeakReference
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    homeUseCase: HomeUseCase
) : ViewModel() {

    interface Delegate {
        fun onUserWithoutInternetError()
        fun onApiError()
        fun onUnknownError()
    }

    var delegate: WeakReference<Delegate?> = WeakReference<Delegate?>(null)

    fun handleError(error: Throwable) {
        when(error) {
            is HttpException -> delegate.get()?.onApiError()
            is IOException -> delegate.get()?.onUserWithoutInternetError()
            is UnknownErrorException -> delegate.get()?.onUnknownError()
        }
    }

    val matches = homeUseCase.getMatches().cachedIn(viewModelScope)
}