package com.manager.api.controllers.v1.delivery

import com.manager.api.domain.requests.FindAllDeliveriesRequest
import com.manager.api.domain.responses.DeliveryResponse
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.http.HttpResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse

interface GetDeliveryOpenApi {
    @Operation(summary = "Busca uma entrega pelo identificador")
    @ApiResponse(responseCode = "200", description = "Entrega encontrada")
    @ApiResponse(responseCode = "400", description = "Requisição inválida")
    @ApiResponse(responseCode = "404", description = "Entrega não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro interno")
    fun findDeliveryById(@Parameter(description = "Identificador da entrega") id: Long): HttpResponse<DeliveryResponse>

    @Operation(summary = "Busca todas as entregas")
    @ApiResponse(responseCode = "200", description = "Entregas encontradas")
    @ApiResponse(responseCode = "400", description = "Requisição inválida")
    @ApiResponse(responseCode = "500", description = "Erro interno")
    fun findAllDeliveries(request: FindAllDeliveriesRequest, pageable: Pageable): HttpResponse<Page<DeliveryResponse>>
}