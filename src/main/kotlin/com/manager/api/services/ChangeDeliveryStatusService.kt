package com.manager.api.services

interface ChangeDeliveryStatusService {
    fun execute(userId: Long, deliveryId: Long)
}