package com.manager.api.services

import com.manager.api.domain.entities.Delivery
import com.manager.api.domain.enums.DeliveryStatus

interface FindAndValidateDeliveryService {
    fun onUpdateStatus(deliveryId: Long, previousStatus: DeliveryStatus): Delivery
}