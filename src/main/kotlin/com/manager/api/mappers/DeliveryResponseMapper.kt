package com.manager.api.mappers

import com.manager.api.domain.entities.Delivery
import com.manager.api.domain.responses.DeliveryResponse

interface DeliveryResponseMapper {
    fun fromEntity(entity: Delivery): DeliveryResponse
}