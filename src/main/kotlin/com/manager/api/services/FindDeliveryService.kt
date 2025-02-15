package com.manager.api.services

import com.manager.api.domain.entities.Delivery
import com.manager.api.domain.responses.FindAllDeliveriesRequest
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable

interface FindDeliveryService {
    fun findAll(request: FindAllDeliveriesRequest, pageable: Pageable): Page<Delivery>
    fun findOrThrow(deliveryId: Long): Delivery
}