package com.manager.api.services.impl

import com.manager.api.clients.DeliveryAuditLogClient
import com.manager.api.domain.constants.AuditLogMessage
import com.manager.api.domain.enums.DeliveryAuditLogEvent
import com.manager.api.domain.repositories.DeliveryRepository
import com.manager.api.domain.requests.RegisterDeliveryAuditLogRequest
import com.manager.api.domain.requests.SaveDeliveryDraftRequest
import com.manager.api.services.UpdateDeliveryDraftService
import com.manager.api.services.ValidateDeliveryDraftService
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton

@Singleton
class UpdateDeliveryDraftServiceImpl(
    private val deliveryAuditLogClient: DeliveryAuditLogClient,
    private val deliveryRepository: DeliveryRepository,
    private val validateDeliveryDraftService: ValidateDeliveryDraftService
) : UpdateDeliveryDraftService {

    @Transactional
    override fun execute(userId: Long, deliveryId: Long, request: SaveDeliveryDraftRequest) {
        val foundDelivery = validateDeliveryDraftService.execute(deliveryId)
        val mappedDelivery = request.updateDelivery(foundDelivery)
        val deliveryAuditLogRequest = buildDeliveryAuditLogRequest(deliveryId, userId)
        deliveryRepository.update(mappedDelivery)
        deliveryAuditLogClient.register(deliveryAuditLogRequest)
    }

    private fun buildDeliveryAuditLogRequest(deliveryId: Long, userId: Long) = RegisterDeliveryAuditLogRequest(
        deliveryId = deliveryId,
        userId = userId,
        event = DeliveryAuditLogEvent.UPDATE_DRAFT,
        description = AuditLogMessage.UPDATE_DRAFT
    )

}