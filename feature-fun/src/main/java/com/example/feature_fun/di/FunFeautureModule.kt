package com.example.feature_fun.di

import com.example.feature_fun.data.mapper.DataModelMapper
import com.example.feature_fun.data.mapper.DataModelMapperImpl
import com.example.feature_fun.data.repository.FunRepositoryImpl
import com.example.feature_fun.domain.repository.FunRepository
import com.example.feature_fun.domain.usecase.GetResultUseCase
import com.example.feature_fun.domain.usecase.GetResultUseCaseImpl
import com.example.feature_fun.domain.usecase.SaveResultUseCase
import com.example.feature_fun.domain.usecase.SaveResultUseCaseImpl
import com.example.feature_fun.presentation.mapper.PresentationMapper
import com.example.feature_fun.presentation.mapper.PresentationMapperImpl
import com.example.feature_fun.presentation.viewmodel.FunViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val funFeatureModule = module {
    viewModel {
        FunViewModel(
            getResultUseCase = get(),
            saveResultUseCase = get(),
            presentationMapper = get()
        )
    }

    single<FunRepository> {
        FunRepositoryImpl(
            mapper = get(),
            dataSourceDbFunApi = get(),
            networkManager = get()
        )
    }

    single<DataModelMapper> { DataModelMapperImpl() }
    factory<PresentationMapper> { PresentationMapperImpl() }
    factory<GetResultUseCase> { GetResultUseCaseImpl(repository = get()) }
    factory<SaveResultUseCase> { SaveResultUseCaseImpl(repository = get()) }
}