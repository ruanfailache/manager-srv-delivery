package com.manager.api.services.impl

import com.manager.api.domain.entities.Delivery
import com.manager.api.domain.exceptions.DeliveryNotFoundException
import com.manager.api.domain.repositories.DeliveryRepository
import com.manager.api.domain.responses.FindAllDeliveriesRequest
import com.manager.api.domain.specifications.DeliverySpecification
import com.manager.api.services.FindDeliveryService
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import jakarta.inject.Singleton

@Singleton
class FindDeliveryServiceImpl(
    private val deliveryRepository: DeliveryRepository
) : FindDeliveryService {

    override fun findAll(request: FindAllDeliveriesRequest, pageable: Pageable): Page<Delivery> {
        val specification = DeliverySpecification.fromFindAllRequest(request)
        return deliveryRepository.findAll(specification, pageable)
    }

    override fun findOrThrow(deliveryId: Long): Delivery {
        return deliveryRepository.findById(deliveryId).orElseThrow {
            DeliveryNotFoundException()
        }
    }
}