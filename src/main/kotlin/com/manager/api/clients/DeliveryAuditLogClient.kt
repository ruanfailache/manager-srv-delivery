package com.manager.api.clients

import com.manager.api.domain.requests.RegisterDeliveryAuditLogRequest
import com.manager.api.domain.responses.RegisterDeliveryAuditLogResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client

@Client(id = "")
interface DeliveryAuditLogClient {
    @Post()
    fun register(@Body() request: RegisterDeliveryAuditLogRequest): RegisterDeliveryAuditLogResponse
}