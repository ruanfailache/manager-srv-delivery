package com.manager.api.services

import com.manager.api.domain.entities.Delivery

interface ValidateDeliveryDraftService {
    fun execute(deliveryId: Long): Delivery
}