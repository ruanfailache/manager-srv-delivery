package com.manager.api.controllers.v1.delivery.put

import com.manager.api.domain.requests.RequestDeliveryChangesRequest
import com.manager.api.domain.requests.SaveDeliveryDraftRequest
import com.manager.api.domain.responses.DeliveryResponse
import com.manager.api.mappers.DeliveryResponseMapper
import com.manager.api.services.UpdateDeliveryDraftService
import com.manager.api.services.UpdateDeliveryStatusService
import io.micronaut.http.HttpHeaders
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Header
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Put

@Controller("/v1/delivery")
class PutDeliveryController(
    private val deliveryResponseMapper: DeliveryResponseMapper,
    private val updateDeliveryDraftService: UpdateDeliveryDraftService,
    private val updateDeliveryStatusService: UpdateDeliveryStatusService
) : PutDeliveryOpenApi {

    @Put("/{id}/draft")
    override fun updateDraft(
        @PathVariable id: Long,
        @Header(HttpHeaders.AUTHORIZATION) bearerToken: String,
        @Body request: SaveDeliveryDraftRequest
    ): HttpResponse<DeliveryResponse> {
        val delivery = updateDeliveryDraftService.updateDraft(bearerToken, 1, id, request)
        val response = deliveryResponseMapper.fromEntity(delivery)
        return HttpResponse.ok(response)
    }

    @Put("/{id}/submit")
    override fun submit(
        @PathVariable id: Long,
        @Header(HttpHeaders.AUTHORIZATION) bearerToken: String
    ): HttpResponse<DeliveryResponse> {
        val delivery = updateDeliveryStatusService.submit(bearerToken, 1, id)
        val response = deliveryResponseMapper.fromEntity(delivery)
        return HttpResponse.ok(response)
    }

    @Put("/{id}/cancel")
    override fun cancel(
        @PathVariable id: Long,
        @Header(HttpHeaders.AUTHORIZATION) bearerToken: String
    ): HttpResponse<DeliveryResponse> {
        val delivery = updateDeliveryStatusService.cancelSubmission(bearerToken, 1, id)
        val response = deliveryResponseMapper.fromEntity(delivery)
        return HttpResponse.ok(response)
    }

    @Put("/{id}/start-analysis")
    override fun startAnalysis(
        @PathVariable id: Long,
        @Header(HttpHeaders.AUTHORIZATION) bearerToken: String
    ): HttpResponse<DeliveryResponse> {
        val delivery = updateDeliveryStatusService.startAnalysis(bearerToken, 1, id)
        val response = deliveryResponseMapper.fromEntity(delivery)
        return HttpResponse.ok(response)
    }

    @Put("/{id}/approve")
    override fun approve(
        @PathVariable id: Long,
        @Header(HttpHeaders.AUTHORIZATION) bearerToken: String
    ): HttpResponse<DeliveryResponse> {
        val delivery = updateDeliveryStatusService.approve(bearerToken, 1, id)
        val response = deliveryResponseMapper.fromEntity(delivery)
        return HttpResponse.ok(response)
    }

    @Put("/{id}/reject")
    override fun reject(
        @PathVariable id: Long,
        @Header(HttpHeaders.AUTHORIZATION) bearerToken: String
    ): HttpResponse<DeliveryResponse> {
        val delivery = updateDeliveryStatusService.reject(bearerToken, 1, id)
        val response = deliveryResponseMapper.fromEntity(delivery)
        return HttpResponse.ok(response)
    }

    @Put("/{id}/request-changes")
    override fun requestChanges(
        @PathVariable id: Long,
        @Header(HttpHeaders.AUTHORIZATION) bearerToken: String,
        @Body request: RequestDeliveryChangesRequest
    ): HttpResponse<DeliveryResponse> {
        val delivery = updateDeliveryStatusService.requestChanges(bearerToken, 1, id, request)
        val response = deliveryResponseMapper.fromEntity(delivery)
        return HttpResponse.ok(response)
    }

}