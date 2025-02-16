package com.manager.api.mappers

import com.manager.api.domain.entities.Delivery
import com.manager.api.domain.requests.SaveDeliveryDraftRequest

interface DeliveryMapper {
    fun createDraft(userId: Long, request: SaveDeliveryDraftRequest): Delivery
    fun updateDraft(delivery: Delivery, request: SaveDeliveryDraftRequest): Delivery
}