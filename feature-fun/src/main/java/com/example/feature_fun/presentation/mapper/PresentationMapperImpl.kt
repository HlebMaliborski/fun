package com.example.feature_fun.presentation.mapper

import com.example.core_common.mapper.Mapper
import com.example.feature_fun.domain.model.DomainScoreModel
import com.example.feature_fun.presentation.model.PresentationModel

class PresentationMapperImpl : PresentationMapper() {
    override fun map(data: DomainScoreModel) = PresentationModel(data.id, data.result)
}

abstract class PresentationMapper : Mapper<DomainScoreModel, PresentationModel>