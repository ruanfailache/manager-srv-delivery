package com.manager.api.mappers

import com.manager.api.domain.entities.Delivery
import com.manager.api.domain.responses.DeliveryResponse
import io.micronaut.data.model.Page

interface DeliveryResponseMapper {
    fun fromEntity(entity: Delivery): DeliveryResponse
    fun fromEntities(entities: Page<Delivery>): Page<DeliveryResponse>
}