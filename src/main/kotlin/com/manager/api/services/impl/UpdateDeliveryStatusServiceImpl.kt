package com.manager.api.services.impl

import com.manager.api.clients.DeliveryAuditLogClient
import com.manager.api.domain.constants.AuditLogMessage
import com.manager.api.domain.enums.DeliveryAuditLogEvent
import com.manager.api.domain.enums.DeliveryStatus
import com.manager.api.domain.repositories.DeliveryRepository
import com.manager.api.domain.requests.RegisterDeliveryAuditLogRequest
import com.manager.api.services.UpdateDeliveryStatusService
import com.manager.api.services.FindDeliveryService
import com.manager.api.services.ValidateDeliveryService
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton

@Singleton
class UpdateDeliveryStatusServiceImpl(
    private val deliveryAuditLogClient: DeliveryAuditLogClient,
    private val deliveryRepository: DeliveryRepository,
    private val findDeliveryService: FindDeliveryService,
    private val validateDeliveryService: ValidateDeliveryService
) : UpdateDeliveryStatusService {

    @Transactional
    override fun submit(userId: Long, deliveryId: Long) {
        val delivery = findDeliveryService.findOrThrow(deliveryId)
        validateDeliveryService.validateStatusAndRequester(delivery, DeliveryStatus.DRAFT, userId)
        delivery.status = DeliveryStatus.SUBMITTED
        deliveryRepository.update(delivery)
        registerAuditLog(deliveryId, userId, DeliveryAuditLogEvent.SUBMIT_DRAFT, AuditLogMessage.SUBMIT_DRAFT)
    }

    @Transactional
    override fun startAnalysis(userId: Long, deliveryId: Long) {
        val delivery = findDeliveryService.findOrThrow(deliveryId)
        validateDeliveryService.validateStatus(delivery, DeliveryStatus.SUBMITTED)

        delivery.reviewerId = userId
        delivery.status = DeliveryStatus.IN_ANALYSIS

        deliveryRepository.update(delivery)

        registerAuditLog(deliveryId, userId, DeliveryAuditLogEvent.START_ANALYSIS, AuditLogMessage.START_ANALYSIS)
    }

    @Transactional
    override fun approve(userId: Long, deliveryId: Long) {
        val delivery = findDeliveryService.findOrThrow(deliveryId)
        validateDeliveryService.validateStatusAndReviewer(delivery, DeliveryStatus.IN_ANALYSIS, userId)
        delivery.status = DeliveryStatus.APPROVED
        deliveryRepository.update(delivery)
        registerAuditLog(deliveryId, userId, DeliveryAuditLogEvent.APPROVE, AuditLogMessage.APPROVE)
    }

    @Transactional
    override fun reject(userId: Long, deliveryId: Long) {
        val delivery = findDeliveryService.findOrThrow(deliveryId)
        validateDeliveryService.validateStatusAndReviewer(delivery, DeliveryStatus.IN_ANALYSIS, userId)
        delivery.status = DeliveryStatus.REJECTED
        deliveryRepository.update(delivery)
        registerAuditLog(deliveryId, userId, DeliveryAuditLogEvent.REJECT, AuditLogMessage.REJECT)
    }

    @Transactional
    override fun requestChanges(userId: Long, deliveryId: Long, description: String) {
        val delivery = findDeliveryService.findOrThrow(deliveryId)
        validateDeliveryService.validateStatusAndReviewer(delivery, DeliveryStatus.IN_ANALYSIS, userId)
        delivery.status = DeliveryStatus.CHANGES_REQUESTED
        deliveryRepository.update(delivery)
        registerAuditLog(deliveryId, userId, DeliveryAuditLogEvent.REQUEST_CHANGES, description)
    }

    private fun registerAuditLog(deliveryId: Long, userId: Long, event: DeliveryAuditLogEvent, description: String) {
        deliveryAuditLogClient.register(
            RegisterDeliveryAuditLogRequest(
                deliveryId = deliveryId,
                userId = userId,
                event = event,
                description = description
            )
        )
    }

}