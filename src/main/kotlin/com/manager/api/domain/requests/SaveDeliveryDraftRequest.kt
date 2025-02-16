package com.manager.api.domain.requests

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

data class SaveDeliveryDraftRequest(
    @NotBlank
    @Schema(description = "Título da entrega")
    val title: String,

    @NotBlank
    @Schema(description = "Descrição da entrega")
    val description: String,
)