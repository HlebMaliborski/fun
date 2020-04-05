package com.example.feature_fun.domain.usecase

import com.example.core_common.result.Either
import com.example.core_common.result.Failure
import com.example.core_domain_api.usecase.BaseUseCase
import com.example.feature_fun.domain.model.DomainScoreModel
import com.example.feature_fun.domain.repository.FunRepository

class GetResultUseCaseImpl(private val repository: FunRepository) : GetResultUseCase() {
    override suspend fun run(params: None): Either<Failure, DomainScoreModel> {
        return when (val result = repository.getBest()) {
            is Either.Error -> result
            is Either.Success -> Either.Success(result.data)
        }
    }
}

abstract class GetResultUseCase : BaseUseCase<DomainScoreModel, BaseUseCase.None>()