package com.manager.api.controllers.v1.delivery.get

import com.manager.api.domain.requests.FindAllDeliveriesRequest
import com.manager.api.domain.responses.DeliveryResponse
import com.manager.api.mappers.DeliveryResponseMapper
import com.manager.api.services.FindDeliveryService
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/v1/delivery")
class GetDeliveryController(
    private val deliveryResponseMapper: DeliveryResponseMapper,
    private val findDeliveryService: FindDeliveryService,
) : GetDeliveryOpenApi {

    @Get("/{id}")
    override fun findDeliveryById(id: Long): HttpResponse<DeliveryResponse> {
        val delivery = findDeliveryService.findOrThrow(id)
        val response = deliveryResponseMapper.fromEntity(delivery)
        return HttpResponse.ok(response)
    }

    @Get
    override fun findAllDeliveries(
        request: FindAllDeliveriesRequest?,
        pageable: Pageable
    ): HttpResponse<Page<DeliveryResponse>> {
        val deliveries = findDeliveryService.findAll(request, pageable)
        val response = deliveryResponseMapper.fromEntities(deliveries)
        return HttpResponse.ok(response)
    }

}