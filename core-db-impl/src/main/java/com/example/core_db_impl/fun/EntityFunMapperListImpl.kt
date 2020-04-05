package com.example.core_db_impl.`fun`

import com.example.core_common.mapper.Mapper
import com.example.core_db_api.`fun`.EntityFun

class EntityFunMapperListImpl : EntityFunMapperList {
    override fun map(data: List<EntityDbFun>): List<EntityFun> = data.map {
        EntityFun(it.id, it.result)
    }
}

interface EntityFunMapperList : Mapper<List<EntityDbFun>, List<EntityFun>>
