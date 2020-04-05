package com.example.feature_fun.presentation.view

import android.os.Bundle
import android.view.View
import com.example.core_common.result.Failure
import com.example.core_navigation_api.FunListNavigation
import com.example.core_navigation_api.TestNavigation
import com.example.core_presentation_api.extensions.fadeIn
import com.example.core_presentation_api.extensions.fadeOut
import com.example.core_presentation_api.extensions.observeViewState
import com.example.core_presentation_api.extensions.visibility
import com.example.core_presentation_api.view.BaseFragment
import com.example.feature_fun.R
import com.example.feature_fun.presentation.viewmodel.FunViewModel
import kotlinx.android.synthetic.main.fragment_fun_layout.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class FunFragment : BaseFragment(R.layout.fragment_fun_layout) {
    private val funViewModel: FunViewModel by viewModel()
    private val navigation: FunListNavigation by inject()
    private val navigationTest: TestNavigation by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewState(funViewModel.viewState) {
            renderViewState(it)
        }

        observeViewState(funViewModel.viewEffects) {
            renderViewEffects(it)
        }

        observeViewState(funViewModel.funDone) {
            if (it) {
                reset()
            }
        }

        observeViewState(funViewModel.currentScore) {
            updateScore(it)
        }

        initializeViews()
    }

    private fun updateScore(result: Int) {
        resultText.text = result.toString()
    }

    private fun reset() {
        clickBtn.visibility(false)
        startBtn.visibility(true)
        startBtn.isEnabled = true
        resultText.text = ""
    }

    private fun initializeViews() {
        startBtn.setOnClickListener {
            funViewModel.onStartFun()
            startBtn.isEnabled = false
        }

        clickBtn.setOnClickListener {
            funViewModel.onButtonClick()
        }
    }

    private fun renderViewState(viewState: FunViewModel.ViewState) {
        setViewVisibility(viewState.isLoading)
        bestResult.text = if (viewState.presentationModel != null) getString(
            R.string.fun_text_best_score,
            viewState.presentationModel.result
        ) else getString(R.string.fun_text_no_score)
        handleFailure(viewState.failure)
    }

    private fun renderViewEffects(viewEffects: FunViewModel.ViewEffects) {
        when (viewEffects) {
            is FunViewModel.ViewEffects.Ready -> {
                resultText.text =
                    getString(R.string.fun_text_ready)
                resultText.fadeIn(900)
                resultText.fadeOut(900)
            }
            is FunViewModel.ViewEffects.Steady -> {
                resultText.text =
                    getString(R.string.fun_text_steady)
                resultText.fadeIn(900)
                resultText.fadeOut(900)
            }
            is FunViewModel.ViewEffects.Go -> {
                resultText.text = getString(R.string.fun_text_go)
                resultText.fadeIn(900)
                clickBtn.visibility(true)
                startBtn.visibility(false)
            }
        }
    }

    private fun setViewVisibility(visibility: Boolean) {
        progressBar.visibility(visibility)
        startBtn.visibility(!visibility)
        bestResult.visibility(!visibility)
        resultText.visibility(!visibility)
    }

    private fun handleFailure(failure: Failure) {
        when (failure) {
            is Failure.NetworkFailure -> notify(R.string.error_message_network)
            is Failure.DatabaseFailure -> notify(R.string.error_message_db)
        }
    }
}