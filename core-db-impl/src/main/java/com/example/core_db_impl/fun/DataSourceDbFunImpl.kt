package com.example.core_db_impl.`fun`

import com.example.core_common.result.Either
import com.example.core_common.result.Failure
import com.example.core_db_api.`fun`.DataSourceDbFunApi
import com.example.core_db_api.`fun`.EntityFun

class DataSourceDbFunImpl(
    private val dao: DaoFun,
    private val entityFunMapper: EntityFunMapper,
    private val entityFunMapperList: EntityFunMapperList
) : DataSourceDbFunApi {
    override suspend fun getBestResult(): Either<Failure, EntityFun> {
        val data = dao.getBest()
        return if (data != null) {
            Either.Success(entityFunMapper.map(data))
        } else {
            Either.Error(Failure.DatabaseFailure.EmptyTable)
        }
    }

    override suspend fun getAllResults(): Either<Failure, List<EntityFun>> {
        val data = dao.getAll()
        return if (data.isEmpty()) {
            Either.Error(Failure.DatabaseFailure.EmptyTable)
        } else {
            Either.Success(entityFunMapperList.map(data))
        }
    }

    override suspend fun saveBest(result: Int) {
        dao.insert(EntityDbFun(result = result))
    }
}