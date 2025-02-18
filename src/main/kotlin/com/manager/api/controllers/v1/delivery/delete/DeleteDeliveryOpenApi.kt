package com.manager.api.controllers.v1.delivery.delete

import io.micronaut.http.HttpResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "Entregas")
interface DeleteDeliveryOpenApi {

    @Operation(summary = "Deleta uma entrega")
    @ApiResponse(responseCode = "204", description = "Entrega deletada")
    @ApiResponse(responseCode = "400", description = "Requisição inválida")
    @ApiResponse(responseCode = "404", description = "Entrega não encontrada")
    fun delete(
        @Parameter(description = "Identificador da entrega") id: Long,
        @Parameter(description = "Token de autorização", hidden = true) bearerToken: String
    ): HttpResponse<Unit>

}