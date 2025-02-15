package com.manager.api.services

interface UpdateDeliveryStatusService {
    fun submit(userId: Long, deliveryId: Long)
    fun startAnalysis(userId: Long, deliveryId: Long)
    fun approve(userId: Long, deliveryId: Long)
    fun reject(userId: Long, deliveryId: Long)
    fun requestChanges(userId: Long, deliveryId: Long, description: String)
}