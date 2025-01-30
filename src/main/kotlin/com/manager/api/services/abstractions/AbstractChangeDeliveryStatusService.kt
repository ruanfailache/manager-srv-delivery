package com.manager.api.services.abstractions

import com.manager.api.clients.DeliveryAuditLogClient
import com.manager.api.domain.entities.Delivery
import com.manager.api.domain.enums.DeliveryAuditLogEvent
import com.manager.api.domain.enums.DeliveryStatus
import com.manager.api.domain.repositories.DeliveryRepository
import com.manager.api.domain.requests.RegisterDeliveryAuditLogRequest
import com.manager.api.services.ChangeDeliveryStatusService
import com.manager.api.services.ValidateDeliveryDraftService
import io.micronaut.transaction.annotation.Transactional

abstract class AbstractChangeDeliveryStatusService(
    private val status: DeliveryStatus,
    private val auditLogEvent: DeliveryAuditLogEvent,
    private val deliveryAuditLogClient: DeliveryAuditLogClient,
    private val deliveryRepository: DeliveryRepository,
    private val validateDeliveryDraftService: ValidateDeliveryDraftService
) : ChangeDeliveryStatusService {

    @Transactional
    override fun execute(userId: Long, deliveryId: Long) {
        val foundDelivery = validateDeliveryDraftService.execute(deliveryId)
        changeStatus(foundDelivery)
        logAuditEvent(deliveryId, userId)
    }

    private fun changeStatus(delivery: Delivery) {
        delivery.status = status
        deliveryRepository.update(delivery)
    }

    private fun logAuditEvent(deliveryId: Long, userId: Long) {
        val auditLogRequest = RegisterDeliveryAuditLogRequest(
            deliveryId = deliveryId,
            userId = userId,
            event = auditLogEvent
        )
        deliveryAuditLogClient.register(auditLogRequest)
    }

}