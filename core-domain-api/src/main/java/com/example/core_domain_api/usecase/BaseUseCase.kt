package com.example.core_domain_api.usecase

import com.example.core_common.result.Either
import com.example.core_common.result.Failure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseUseCase<out Type, in Params> where Type : Any {
    open suspend operator fun invoke(
        params: Params,
        onResult: (Either<Failure, Type>) -> Unit = {}
    ) = withContext(Dispatchers.IO) {
        run(params)
    }

    abstract suspend fun run(params: Params): Either<Failure, Type>

    class None
}