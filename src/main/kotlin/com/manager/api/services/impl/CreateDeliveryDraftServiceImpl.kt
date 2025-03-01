package com.manager.api.services.impl

import com.manager.api.clients.DeliveryAuditLogClient
import com.manager.api.domain.constants.AuditLogMessage
import com.manager.api.domain.entities.Delivery
import com.manager.api.domain.enums.DeliveryAuditLogEvent
import com.manager.api.domain.repositories.DeliveryRepository
import com.manager.api.domain.requests.RegisterDeliveryAuditLogRequest
import com.manager.api.domain.requests.SaveDeliveryDraftRequest
import com.manager.api.mappers.DeliveryMapper
import com.manager.api.services.CreateDeliveryDraftService
import io.micronaut.aop.Around
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton

@Around
@Singleton
class CreateDeliveryDraftServiceImpl(
    private val deliveryAuditLogClient: DeliveryAuditLogClient,
    private val deliveryMapper: DeliveryMapper,
    private val deliveryRepository: DeliveryRepository
) : CreateDeliveryDraftService {

    @Transactional
    override fun create(bearerToken: String, userId: Long, request: SaveDeliveryDraftRequest): Delivery {
        val mappedEntity = deliveryMapper.createDraft(userId, request)
        val savedEntity = deliveryRepository.save(mappedEntity)
        val auditLogRequest = buildAuditLogRequest(savedEntity)
        deliveryAuditLogClient.register(bearerToken, auditLogRequest)
        return savedEntity
    }

    private fun buildAuditLogRequest(savedEntity: Delivery) = RegisterDeliveryAuditLogRequest(
        deliveryId = savedEntity.id!!,
        event = DeliveryAuditLogEvent.CREATE_DRAFT,
        description = AuditLogMessage.CREATE_DRAFT
    )

}