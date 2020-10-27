package com.example.mvvmhilttut.api

import com.example.mvvmhilttut.model.ActivityModel
import com.example.mvvmhilttut.util.EntityMapper
import javax.inject.Inject

class ApiActivityMapper @Inject constructor() : EntityMapper<ActivityEntity, ActivityModel> {

    override fun mapFromEntity(entity: ActivityEntity) =
        ActivityModel(
            accessibility = entity.accessibility,
            activity = entity.activity,
            participants = entity.participants,
            price = entity.price,
            type = entity.type
        )


    override fun mapToEntity(domainModel: ActivityModel) =
        ActivityEntity(
            accessibility = domainModel.accessibility,
            activity = domainModel.activity,
            participants = domainModel.participants,
            price = domainModel.price,
            type = domainModel.type,
            link = "",
            key = ""
        )


    fun mapFromEntityList(entities: List<ActivityEntity>) =
        entities.map { mapFromEntity(it) }

}
