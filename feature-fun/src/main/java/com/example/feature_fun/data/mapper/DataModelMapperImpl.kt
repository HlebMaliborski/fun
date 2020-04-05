package com.example.feature_fun.data.mapper

import com.example.core_common.mapper.Mapper
import com.example.core_db_api.`fun`.EntityFun
import com.example.feature_fun.domain.model.DomainScoreModel

class DataModelMapperImpl : DataModelMapper {
    override fun map(data: EntityFun) = DomainScoreModel(data.id, data.result)
}

interface DataModelMapper : Mapper<EntityFun, DomainScoreModel>