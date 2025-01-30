package com.manager.api.domain.requests

import com.manager.api.domain.enums.DeliveryAuditLogEvent

data class RegisterDeliveryAuditLogRequest(
    val event: DeliveryAuditLogEvent,
    val deliveryId: Long,
    val userId: Long,
)
