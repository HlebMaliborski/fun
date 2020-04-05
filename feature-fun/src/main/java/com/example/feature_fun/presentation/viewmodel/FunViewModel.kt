package com.example.feature_fun.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.core_common.result.Either
import com.example.core_common.result.Failure
import com.example.core_domain_api.usecase.BaseUseCase
import com.example.core_presentation_api.viewmodel.BaseViewModel
import com.example.feature_fun.domain.model.DomainScoreModel
import com.example.feature_fun.domain.usecase.GetResultUseCase
import com.example.feature_fun.domain.usecase.SaveParams
import com.example.feature_fun.domain.usecase.SaveResultUseCase
import com.example.feature_fun.presentation.mapper.PresentationMapper
import com.example.feature_fun.presentation.model.PresentationModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

class FunViewModel(
    private val getResultUseCase: GetResultUseCase,
    private val saveResultUseCase: SaveResultUseCase,
    private val presentationMapper: PresentationMapper
) : BaseViewModel() {
    private var score: Int = 0
    private val _viewState = liveData {
        emit(ViewState())
        delay(3000)//just emulate some operation
        val state = handleResult(getResultUseCase.invoke(BaseUseCase.None()))
        emit(state)
    } as MutableLiveData
    val viewState: LiveData<ViewState> = _viewState

    private val _viewEffects = MutableLiveData<ViewEffects>()
    val viewEffects: LiveData<ViewEffects> = _viewEffects

    private val _funDone = MutableLiveData<Boolean>()
    val funDone: LiveData<Boolean> = _funDone

    private val _currentScore = MutableLiveData<Int>()
    val currentScore = _currentScore

    fun onStartFun() {
        score = 0
        viewModelScope.launch {
            emitViewEffects().onCompletion {
                delay(5000)
                done()
            }.collect {
                _viewEffects.value = it
            }
        }
    }

    fun onButtonClick() {
        score++
        currentScore.value = score
    }

    private fun done() {
        _funDone.value = true
        viewModelScope.launch {
            _viewState.value = handleResult(saveResultUseCase.invoke(SaveParams(score)))
        }
    }

    private fun emitViewEffects() = flow {
        emit(ViewEffects.Ready)
        delay(1000)
        emit(ViewEffects.Steady)
        delay(1000)
        emit(ViewEffects.Go)
    }

    private fun currentViewState(): ViewState = _viewState.value!!
    private fun currentViewEffects(): ViewEffects = _viewEffects.value!!

    private fun handleResult(result: Either<Failure, DomainScoreModel>): ViewState {
        return when (result) {
            is Either.Error -> currentViewState().copy(isLoading = false, failure = result.a)
            is Either.Success -> currentViewState().copy(
                isLoading = false,
                failure = Failure.None,
                presentationModel = presentationMapper.map(result.data)
            )
        }
    }

    data class ViewState(
        val isLoading: Boolean = true,
        val failure: Failure = Failure.None,
        val presentationModel: PresentationModel? = null
    )

    sealed class ViewEffects {
        object Ready : ViewEffects()
        object Steady : ViewEffects()
        object Go : ViewEffects()
    }
}