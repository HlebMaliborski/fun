package com.example.feature_fun.domain.usecase

import com.example.core_common.result.Either
import com.example.core_common.result.Failure
import com.example.core_domain_api.usecase.BaseUseCase
import com.example.feature_fun.domain.model.DomainScoreModel
import com.example.feature_fun.domain.repository.FunRepository

class SaveResultUseCaseImpl(private val repository: FunRepository) : SaveResultUseCase() {
    override suspend fun run(params: SaveParams): Either<Failure, DomainScoreModel> {
        repository.saveBest(params.result)

        return when (val result = repository.getBest()) {
            is Either.Error -> result
            is Either.Success -> Either.Success(result.data)
        }
    }
}

abstract class SaveResultUseCase : BaseUseCase<DomainScoreModel, SaveParams>()
data class SaveParams(val result: Int)