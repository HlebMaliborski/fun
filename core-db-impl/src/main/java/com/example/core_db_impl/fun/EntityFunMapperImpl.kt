package com.example.core_db_impl.`fun`

import com.example.core_common.mapper.Mapper
import com.example.core_db_api.`fun`.EntityFun

class EntityFunMapperImpl : EntityFunMapper {
    override fun map(data: EntityDbFun): EntityFun = EntityFun(data.id, data.result)
}

interface EntityFunMapper : Mapper<EntityDbFun, EntityFun>
