package com.manager.api.domain.requests

import com.manager.api.domain.enums.DeliveryStatus
import io.swagger.v3.oas.annotations.media.Schema

data class FindAllDeliveriesRequest(
    @Schema(description = "Titulo da entrega")
    val title: String?,

    @Schema(description = "Estado da entrega")
    val status: DeliveryStatus?,

    @Schema(description = "Identificador do solicitante")
    val requesterId: Long?,

    @Schema(description = "Identificador do revisor")
    val reviewerId: Long?
)