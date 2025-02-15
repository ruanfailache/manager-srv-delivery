package com.manager.api.services

interface DeleteDeliveryDraftService {
    fun delete(bearerToken: String, userId: Long, deliveryId: Long)
}