package com.manager.api.controllers.v1.delivery.post

import com.manager.api.domain.requests.SaveDeliveryDraftRequest
import com.manager.api.domain.responses.DeliveryResponse
import io.micronaut.http.HttpResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "Entregas")
interface PostDeliveryOpenApi {

    @Operation(summary = "Cria uma entrega em rascunho")
    @ApiResponse(responseCode = "201", description = "Entrega criada")
    @ApiResponse(responseCode = "400", description = "Requisição inválida")
    fun create(
        @Parameter(description = "Token de autorização", hidden = true) bearerToken: String,
        request: SaveDeliveryDraftRequest
    ): HttpResponse<DeliveryResponse>

}