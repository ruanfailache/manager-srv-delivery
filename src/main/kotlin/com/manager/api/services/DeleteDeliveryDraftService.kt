package com.manager.api.services

interface DeleteDeliveryDraftService {
    fun delete(userId: Long, deliveryId: Long)
}