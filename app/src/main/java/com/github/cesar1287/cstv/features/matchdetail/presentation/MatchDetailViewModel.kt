package com.github.cesar1287.cstv.features.matchdetail.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.cesar1287.cstv.base.BaseViewModel
import com.github.cesar1287.cstv.features.matchdetail.domain.MatchDetailUseCase
import com.github.cesar1287.cstv.model.vo.TeamsVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchDetailViewModel @Inject constructor(
    private val matchDetailUseCase: MatchDetailUseCase
) : BaseViewModel() {

    private val _onTeamsLoaded: MutableLiveData<TeamsVO?> = MutableLiveData()

    val onTeamsLoaded: LiveData<TeamsVO?>
        get() = _onTeamsLoaded

    fun getTeams(teamAId: Int, teamBId: Int) {
        viewModelScope.launch {
            callApi(
                call = suspend { matchDetailUseCase.getTeams(teamAId, teamBId) },
                onSuccess = {
                    val data = it as? TeamsVO
                    _onTeamsLoaded.postValue(data)
                }
            )
        }
    }
}