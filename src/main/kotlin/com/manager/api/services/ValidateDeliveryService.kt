package com.manager.api.services

import com.manager.api.domain.entities.Delivery
import com.manager.api.domain.enums.DeliveryStatus

interface ValidateDeliveryService {
    fun validateStatus(delivery: Delivery, previousStatus: DeliveryStatus)
    fun validateStatusAndReviewer(delivery: Delivery, previousStatus: DeliveryStatus, reviewerId: Long)
    fun validateStatusAndRequester(delivery: Delivery, previousStatus: DeliveryStatus, requesterId: Long)
}