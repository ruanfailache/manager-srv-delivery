package com.manager.api.services.impl

import com.manager.api.clients.DeliveryAuditLogClient
import com.manager.api.domain.enums.DeliveryAuditLogEvent
import com.manager.api.domain.requests.RegisterDeliveryAuditLogRequest
import com.manager.api.services.DeliveryAuditLogService
import jakarta.inject.Singleton

@Singleton
class DeliveryAuditLogServiceImpl(
    private val deliveryAuditLogClient: DeliveryAuditLogClient
) : DeliveryAuditLogService {

    override fun log(userId: Long, deliveryId: Long, event: DeliveryAuditLogEvent) {
        val auditLogRequest = RegisterDeliveryAuditLogRequest(
            deliveryId = deliveryId,
            userId = userId,
            event = DeliveryAuditLogEvent.UPDATE_DRAFT
        )
        deliveryAuditLogClient.register(auditLogRequest)
    }
    
}