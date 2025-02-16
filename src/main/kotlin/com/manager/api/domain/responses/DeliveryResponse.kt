package com.manager.api.domain.responses

import com.manager.api.domain.enums.DeliveryStatus
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

data class DeliveryResponse(
    @Schema(description = "Identificador da entrega", example = "1")
    val id: Long,

    @Schema(description = "Título da entrega", example = "Relatório de desempenho")
    val title: String,

    @Schema(description = "Descrição da entrega", example = "Relatório de desempenho da equipe de vendas")
    val description: String,

    @Schema(description = "Estado da entrega", example = "DRAFT")
    val status: DeliveryStatus,

    @Schema(description = "Identificador do solicitante", example = "1")
    val requesterId: Long,

    @Schema(description = "Identificador do revisor", example = "2")
    val reviewerId: Long?,

    @Schema(description = "Data de criação da entrega", example = "2021-08-01T00:00:00")
    val createdAt: LocalDateTime,
)