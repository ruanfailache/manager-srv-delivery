package com.manager.api.clients

import com.manager.api.domain.requests.RegisterDeliveryAuditLogRequest
import com.manager.api.domain.responses.RegisterDeliveryAuditLogResponse
import io.micronaut.http.HttpHeaders
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Header
import io.micronaut.http.client.annotation.Client

@Client(id = "")
interface DeliveryAuditLogClient {
    @Post()
    fun register(
        @Header(HttpHeaders.AUTHORIZATION) bearerToken: String,
        @Body() request: RegisterDeliveryAuditLogRequest
    ): RegisterDeliveryAuditLogResponse

    @Delete("/{deliveryId}")
    fun delete(
        @Header(HttpHeaders.AUTHORIZATION) bearerToken: String,
        @PathVariable deliveryId: Long
    )
}