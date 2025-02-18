package com.manager.api.controllers.v1.delivery.put

import com.manager.api.domain.requests.RequestDeliveryChangesRequest
import com.manager.api.domain.requests.SaveDeliveryDraftRequest
import com.manager.api.domain.responses.DeliveryResponse
import io.micronaut.http.HttpResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "Entregas")
interface PutDeliveryOpenApi {

    @Operation(summary = "Atualiza uma entrega em rascunho")
    @ApiResponse(responseCode = "200", description = "Entrega atualizada")
    @ApiResponse(responseCode = "400", description = "Requisição inválida")
    @ApiResponse(responseCode = "404", description = "Entrega não encontrada")
    fun updateDraft(
        @Parameter(description = "Identificador da entrega") id: Long,
        @Parameter(description = "Token de autorização", hidden = true) bearerToken: String,
        request: SaveDeliveryDraftRequest
    ): HttpResponse<DeliveryResponse>

    @Operation(summary = "Submete uma entrega")
    @ApiResponse(responseCode = "200", description = "Entrega submetida")
    @ApiResponse(responseCode = "400", description = "Requisição inválida")
    @ApiResponse(responseCode = "404", description = "Entrega não encontrada")
    fun submit(
        @Parameter(description = "Identificador da entrega") id: Long,
        @Parameter(description = "Token de autorização", hidden = true) bearerToken: String
    ): HttpResponse<DeliveryResponse>

    @Operation(summary = "Cancela a submissão de uma entrega")
    @ApiResponse(responseCode = "200", description = "Entrega com submissão cancelada")
    @ApiResponse(responseCode = "400", description = "Requisição inválida")
    @ApiResponse(responseCode = "404", description = "Entrega não encontrada")
    fun cancel(
        @Parameter(description = "Identificador da entrega") id: Long,
        @Parameter(description = "Token de autorização", hidden = true) bearerToken: String,
    ): HttpResponse<DeliveryResponse>

    @Operation(summary = "Inicia a análise de uma entrega")
    @ApiResponse(responseCode = "200", description = "Entrega com análise iniciada")
    @ApiResponse(responseCode = "400", description = "Requisição inválida")
    @ApiResponse(responseCode = "404", description = "Entrega não encontrada")
    fun startAnalysis(
        @Parameter(description = "Identificador da entrega") id: Long,
        @Parameter(description = "Token de autorização", hidden = true) bearerToken: String,
    ): HttpResponse<DeliveryResponse>

    @Operation(summary = "Aprova uma entrega")
    @ApiResponse(responseCode = "200", description = "Entrega com análise finalizada")
    @ApiResponse(responseCode = "400", description = "Requisição inválida")
    @ApiResponse(responseCode = "404", description = "Entrega não encontrada")
    fun approve(
        @Parameter(description = "Identificador da entrega") id: Long,
        @Parameter(description = "Token de autorização", hidden = true) bearerToken: String,
    ): HttpResponse<DeliveryResponse>

    @Operation(summary = "Rejeita uma entrega")
    @ApiResponse(responseCode = "200", description = "Entrega rejeitada")
    @ApiResponse(responseCode = "400", description = "Requisição inválida")
    @ApiResponse(responseCode = "404", description = "Entrega não encontrada")
    fun reject(
        @Parameter(description = "Identificador da entrega") id: Long,
        @Parameter(description = "Token de autorização", hidden = true) bearerToken: String,
    ): HttpResponse<DeliveryResponse>

    @Operation(summary = "Solicita alterações em uma entrega")
    @ApiResponse(responseCode = "200", description = "Entrega com solicitação de alterações")
    @ApiResponse(responseCode = "400", description = "Requisição inválida")
    @ApiResponse(responseCode = "404", description = "Entrega não encontrada")
    fun requestChanges(
        @Parameter(description = "Identificador da entrega") id: Long,
        @Parameter(description = "Token de autorização", hidden = true) bearerToken: String,
        request: RequestDeliveryChangesRequest
    ): HttpResponse<DeliveryResponse>

}