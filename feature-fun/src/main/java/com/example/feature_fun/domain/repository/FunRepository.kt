package com.example.feature_fun.domain.repository

import com.example.core_common.result.Either
import com.example.core_common.result.Failure
import com.example.feature_fun.domain.model.DomainScoreModel

interface FunRepository {
    suspend fun getBest(): Either<Failure, DomainScoreModel>
    suspend fun getAll(): Either<Failure, List<DomainScoreModel>>
    suspend fun saveBest(value: Int)
}