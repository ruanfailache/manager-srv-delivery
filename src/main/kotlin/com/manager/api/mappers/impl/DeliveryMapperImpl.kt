package com.manager.api.mappers.impl

import com.manager.api.domain.entities.Delivery
import com.manager.api.domain.enums.DeliveryStatus
import com.manager.api.domain.requests.SaveDeliveryDraftRequest
import com.manager.api.mappers.DeliveryMapper
import jakarta.inject.Singleton

@Singleton
class DeliveryMapperImpl : DeliveryMapper {
    override fun createDraft(userId: Long, request: SaveDeliveryDraftRequest): Delivery = Delivery(
        title = request.title,
        description = request.description,
        requesterId = userId,
        status = DeliveryStatus.DRAFT,
    )

    override fun updateDraft(delivery: Delivery, request: SaveDeliveryDraftRequest): Delivery = delivery.copy(
        title = request.title,
        description = request.description,
    )
}