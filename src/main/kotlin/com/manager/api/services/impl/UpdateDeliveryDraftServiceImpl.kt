package com.manager.api.services.impl

import com.manager.api.clients.DeliveryAuditLogClient
import com.manager.api.domain.constants.AuditLogMessage
import com.manager.api.domain.enums.DeliveryAuditLogEvent
import com.manager.api.domain.enums.DeliveryStatus
import com.manager.api.domain.repositories.DeliveryRepository
import com.manager.api.domain.requests.RegisterDeliveryAuditLogRequest
import com.manager.api.domain.requests.SaveDeliveryDraftRequest
import com.manager.api.mappers.DeliveryMapper
import com.manager.api.services.UpdateDeliveryDraftService
import com.manager.api.services.FindDeliveryService
import com.manager.api.services.ValidateDeliveryService
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton

@Singleton
class UpdateDeliveryDraftServiceImpl(
    private val deliveryAuditLogClient: DeliveryAuditLogClient,
    private val deliveryMapper: DeliveryMapper,
    private val deliveryRepository: DeliveryRepository,
    private val findDeliveryService: FindDeliveryService,
    private val validateDeliveryService: ValidateDeliveryService
) : UpdateDeliveryDraftService {

    @Transactional
    override fun updateDraft(bearerToken: String, userId: Long, deliveryId: Long, request: SaveDeliveryDraftRequest) {
        val delivery = findDeliveryService.findOrThrow(deliveryId)
        validateDeliveryService.validateStatusAndRequester(delivery, DeliveryStatus.DRAFT, userId)
        val updatedDelivery = deliveryMapper.updateDraft(delivery, request)
        deliveryRepository.update(updatedDelivery)
        val auditLogRequest = buildAuditLogRequest(deliveryId)
        deliveryAuditLogClient.register(bearerToken, auditLogRequest)
    }

    private fun buildAuditLogRequest(deliveryId: Long) = RegisterDeliveryAuditLogRequest(
        deliveryId = deliveryId,
        event = DeliveryAuditLogEvent.UPDATE_DRAFT,
        description = AuditLogMessage.UPDATE_DRAFT
    )

}