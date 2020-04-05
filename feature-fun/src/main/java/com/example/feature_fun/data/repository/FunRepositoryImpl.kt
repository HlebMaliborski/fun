package com.example.feature_fun.data.repository

import com.example.core_common.network.NetworkManager
import com.example.core_common.result.Either
import com.example.core_common.result.Failure
import com.example.core_db_api.`fun`.DataSourceDbFunApi
import com.example.feature_fun.data.mapper.DataModelMapper
import com.example.feature_fun.domain.model.DomainScoreModel
import com.example.feature_fun.domain.repository.FunRepository

class FunRepositoryImpl(
    private val mapper: DataModelMapper,
    private val dataSourceDbFunApi: DataSourceDbFunApi,
    private val networkManager: NetworkManager
) : FunRepository {
    override suspend fun getBest(): Either<Failure, DomainScoreModel> {
        return when (val result = dataSourceDbFunApi.getBestResult()) {
            is Either.Error -> result
            is Either.Success -> Either.Success(mapper.map(result.data))
        }
    }

    override suspend fun getAll(): Either<Failure, List<DomainScoreModel>> {
        return when (val result = dataSourceDbFunApi.getAllResults()) {
            is Either.Error -> result
            is Either.Success -> {
                return Either.Success(result.data.map {
                    mapper.map(it)
                })
            }
        }
    }

    override suspend fun saveBest(value: Int) {
        dataSourceDbFunApi.saveBest(value)
    }
}