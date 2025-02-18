package com.manager.api.domain.requests

import io.swagger.v3.oas.annotations.media.Schema

data class RequestDeliveryChangesRequest(
    @Schema(description = "Descrição das mudanças solicitadas")
    val description: String
)