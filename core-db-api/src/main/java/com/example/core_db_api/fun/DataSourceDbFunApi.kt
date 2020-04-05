package com.example.core_db_api.`fun`

import com.example.core_common.result.Either
import com.example.core_common.result.Failure

interface DataSourceDbFunApi {
    suspend fun getBestResult(): Either<Failure, EntityFun>
    suspend fun getAllResults(): Either<Failure, List<EntityFun>>
    suspend fun saveBest(result: Int)
}