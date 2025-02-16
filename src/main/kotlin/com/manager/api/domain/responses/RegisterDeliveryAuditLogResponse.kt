package com.manager.api.domain.responses

import com.manager.api.domain.enums.DeliveryAuditLogEvent
import io.swagger.v3.oas.annotations.media.Schema

data class RegisterDeliveryAuditLogResponse(
    @Schema(description = "Sucesso da operação", example = "true")
    val success: Boolean,

    @Schema(description = "Código de retorno", example = "CREATE_DRAFT")
    val event: DeliveryAuditLogEvent,

    @Schema(description = "Mensagem de retorno", example = "Evento de auditoria registrado com sucesso")
    val message: String,
)
