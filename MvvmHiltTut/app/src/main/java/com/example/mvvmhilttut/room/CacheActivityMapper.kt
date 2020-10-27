package com.example.mvvmhilttut.room

import com.example.mvvmhilttut.api.ActivityEntity
import com.example.mvvmhilttut.model.ActivityModel
import com.example.mvvmhilttut.util.EntityMapper
import javax.inject.Inject

class CacheActivityMapper @Inject constructor(): EntityMapper<ActivityCacheEntity, ActivityModel> {

    override fun mapFromEntity(entity: ActivityCacheEntity) =
        ActivityModel(
            id = entity.id,
            accessibility = entity.accessibility,
            activity = entity.activity,
            participants = entity.participants,
            price = entity.price,
            type = entity.type
        )


    override fun mapToEntity(domainModel: ActivityModel) =
        ActivityCacheEntity(
            id = domainModel.id,
            accessibility = domainModel.accessibility,
            activity = domainModel.activity,
            participants = domainModel.participants,
            price = domainModel.price,
            type = domainModel.type
        )


    fun mapFromEntityList(entities: List<ActivityCacheEntity>) =
        entities.map { mapFromEntity(it) }

}