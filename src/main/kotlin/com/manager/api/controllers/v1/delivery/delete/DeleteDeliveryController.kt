package com.manager.api.controllers.v1.delivery.delete

import com.manager.api.domain.requests.SaveDeliveryDraftRequest
import com.manager.api.domain.responses.DeliveryResponse
import com.manager.api.mappers.DeliveryResponseMapper
import com.manager.api.services.CreateDeliveryDraftService
import com.manager.api.services.DeleteDeliveryDraftService
import io.micronaut.http.HttpHeaders
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Header
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post

@Controller("/v1/delivery")
class DeleteDeliveryController(
    private val deleteDeliveryDraftService: DeleteDeliveryDraftService
) : DeleteDeliveryOpenApi {

    @Delete("/{id}")
    override fun delete(
        @PathVariable id: Long,
        @Header(HttpHeaders.AUTHORIZATION) bearerToken: String
    ): HttpResponse<Unit> {
        deleteDeliveryDraftService.delete(bearerToken, 1, id)
        return HttpResponse.noContent()
    }

}