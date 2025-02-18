package com.manager.api.mappers.impl

import com.manager.api.domain.entities.Delivery
import com.manager.api.domain.responses.DeliveryResponse
import com.manager.api.mappers.DeliveryResponseMapper
import io.micronaut.data.model.Page
import jakarta.inject.Singleton

@Singleton
class DeliveryResponseMapperImpl : DeliveryResponseMapper {
    override fun fromEntity(entity: Delivery): DeliveryResponse = DeliveryResponse(
        id = entity.id!!,
        title = entity.title,
        description = entity.description,
        status = entity.status,
        requesterId = entity.requesterId,
        reviewerId = entity.reviewerId,
        createdAt = entity.createdAt,
    )

    override fun fromEntities(entities: Page<Delivery>): Page<DeliveryResponse> {
        return entities.map { fromEntity(it) }
    }
}