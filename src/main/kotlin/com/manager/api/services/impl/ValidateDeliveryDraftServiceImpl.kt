package com.manager.api.services.impl

import com.manager.api.domain.entities.Delivery
import com.manager.api.domain.enums.DeliveryStatus
import com.manager.api.domain.exceptions.DeliveryNotFoundException
import com.manager.api.domain.exceptions.InvalidDeliveryStatusException
import com.manager.api.domain.repositories.DeliveryRepository
import com.manager.api.services.ValidateDeliveryDraftService
import jakarta.inject.Singleton

@Singleton
class ValidateDeliveryDraftServiceImpl(
    private val deliveryRepository: DeliveryRepository
) : ValidateDeliveryDraftService {
    override fun execute(deliveryId: Long): Delivery {
        val foundDelivery = deliveryRepository.findById(deliveryId).orElseThrow {
            DeliveryNotFoundException()
        }
        if (foundDelivery.status != DeliveryStatus.DRAFT) {
            throw InvalidDeliveryStatusException()
        }
        return foundDelivery
    }
}