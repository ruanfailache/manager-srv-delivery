package com.manager.api.services

import com.manager.api.domain.entities.Delivery
import com.manager.api.domain.requests.RequestDeliveryChangesRequest

interface UpdateDeliveryStatusService {
    fun submit(bearerToken: String, userId: Long, deliveryId: Long): Delivery
    fun cancelSubmission(bearerToken: String, userId: Long, deliveryId: Long): Delivery
    fun startAnalysis(bearerToken: String, userId: Long, deliveryId: Long): Delivery
    fun approve(bearerToken: String, userId: Long, deliveryId: Long): Delivery
    fun reject(bearerToken: String, userId: Long, deliveryId: Long): Delivery
    fun requestChanges(bearerToken: String, userId: Long, deliveryId: Long, request: RequestDeliveryChangesRequest): Delivery
}