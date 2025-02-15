package com.manager.api.services

import com.manager.api.domain.entities.Delivery
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable

interface FindDeliveryService {
    fun findAll(): List<Delivery>
    fun findAll(pageable: Pageable): Page<Delivery>
    fun findOrThrow(deliveryId: Long): Delivery
}