package com.manager.api.services.impl

import com.manager.api.domain.entities.Delivery
import com.manager.api.domain.enums.DeliveryStatus
import com.manager.api.domain.exceptions.DeliveryNotFoundException
import com.manager.api.domain.exceptions.InvalidDeliveryStatusException
import com.manager.api.domain.repositories.DeliveryRepository
import com.manager.api.services.FindAndValidateDeliveryService
import jakarta.inject.Singleton

@Singleton
class FindAndValidateDeliveryServiceImpl(private val deliveryRepository: DeliveryRepository) : FindAndValidateDeliveryService {

    override fun onUpdateStatus(deliveryId: Long, previousStatus: DeliveryStatus): Delivery {
        val foundDelivery = deliveryRepository.findById(deliveryId).orElseThrow {
            DeliveryNotFoundException()
        }
        if (foundDelivery.status != previousStatus) {
            throw InvalidDeliveryStatusException()
        }
        return foundDelivery
    }

}