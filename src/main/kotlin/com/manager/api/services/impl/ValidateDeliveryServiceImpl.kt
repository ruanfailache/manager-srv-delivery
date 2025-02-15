package com.manager.api.services.impl

import com.manager.api.domain.entities.Delivery
import com.manager.api.domain.enums.DeliveryStatus
import com.manager.api.domain.exceptions.DeliveryForbiddenUpdateException
import com.manager.api.domain.exceptions.InvalidDeliveryStatusException
import com.manager.api.services.ValidateDeliveryService
import jakarta.inject.Singleton

@Singleton
class ValidateDeliveryServiceImpl : ValidateDeliveryService {
    override fun validateStatus(delivery: Delivery, previousStatus: DeliveryStatus) {
        if (delivery.status != previousStatus) throw InvalidDeliveryStatusException()
    }

    override fun validateStatusAndReviewer(delivery: Delivery, previousStatus: DeliveryStatus, reviewerId: Long) {
        if (delivery.reviewerId != reviewerId) throw DeliveryForbiddenUpdateException()
        validateStatus(delivery, previousStatus)
    }

    override fun validateStatusAndRequester(delivery: Delivery, previousStatus: DeliveryStatus, requesterId: Long) {
        if (delivery.requesterId != requesterId) throw DeliveryForbiddenUpdateException()
        validateStatus(delivery, previousStatus)
    }
}