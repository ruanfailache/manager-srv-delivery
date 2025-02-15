package com.manager.api.services

interface UpdateDeliveryStatusService {
    fun submit(bearerToken: String, userId: Long, deliveryId: Long)
    fun cancelSubmission(bearerToken: String, userId: Long, deliveryId: Long)
    fun startAnalysis(bearerToken: String, userId: Long, deliveryId: Long)
    fun approve(bearerToken: String, userId: Long, deliveryId: Long)
    fun reject(bearerToken: String, userId: Long, deliveryId: Long)
    fun requestChanges(bearerToken: String, userId: Long, deliveryId: Long, description: String)
}