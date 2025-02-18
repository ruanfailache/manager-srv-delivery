package com.manager.api.controllers.v1.delivery.post

import com.manager.api.domain.requests.FindAllDeliveriesRequest
import com.manager.api.domain.requests.SaveDeliveryDraftRequest
import com.manager.api.domain.responses.DeliveryResponse
import com.manager.api.mappers.DeliveryResponseMapper
import com.manager.api.services.CreateDeliveryDraftService
import com.manager.api.services.FindDeliveryService
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.http.HttpHeaders
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Header
import io.micronaut.http.annotation.Post

@Controller("/v1/delivery")
class PostDeliveryController(
    private val deliveryResponseMapper: DeliveryResponseMapper,
    private val createDeliveryDraftService: CreateDeliveryDraftService
) : PostDeliveryOpenApi {

    @Post
    override fun create(
        @Header(HttpHeaders.AUTHORIZATION) bearerToken: String,
        @Body request: SaveDeliveryDraftRequest
    ): HttpResponse<DeliveryResponse> {
        val delivery = createDeliveryDraftService.create(bearerToken, 1, request)
        val response = deliveryResponseMapper.fromEntity(delivery)
        return HttpResponse.created(response)
    }

}