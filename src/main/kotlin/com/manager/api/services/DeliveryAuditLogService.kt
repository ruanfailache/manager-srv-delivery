package com.manager.api.services

import com.manager.api.domain.enums.DeliveryAuditLogEvent

interface DeliveryAuditLogService {
    fun log(userId: Long, deliveryId: Long, event: DeliveryAuditLogEvent)
}