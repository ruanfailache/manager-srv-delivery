package com.manager.api.domain.requests

import com.manager.api.domain.enums.DeliveryAuditLogEvent
import io.swagger.v3.oas.annotations.media.Schema

data class RegisterDeliveryAuditLogRequest(
    @Schema(description = "Evento de auditoria")
    val event: DeliveryAuditLogEvent,

    @Schema(description = "Identificador da entrega")
    val deliveryId: Long,

    @Schema(description = "Descrição do evento")
    val description: String,
)
