package com.manager.api.services.impl

import com.manager.api.clients.DeliveryAuditLogClient
import com.manager.api.domain.constants.AuditLogMessage
import com.manager.api.domain.entities.Delivery
import com.manager.api.domain.enums.DeliveryAuditLogEvent
import com.manager.api.domain.enums.DeliveryStatus
import com.manager.api.domain.repositories.DeliveryRepository
import com.manager.api.domain.requests.RegisterDeliveryAuditLogRequest
import com.manager.api.domain.requests.RequestDeliveryChangesRequest
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
    override fun submit(bearerToken: String, userId: Long, deliveryId: Long): Delivery {
        val delivery = findDeliveryService.findOrThrow(deliveryId)
        validateDeliveryService.validateStatusAndRequester(delivery, DeliveryStatus.DRAFT, userId)
        delivery.status = DeliveryStatus.SUBMITTED
        val updatedDelivery = deliveryRepository.update(delivery)
        registerAuditLog(bearerToken, deliveryId, AuditLogMessage.SUBMIT_DRAFT, DeliveryAuditLogEvent.SUBMIT_DRAFT)
        return updatedDelivery
    }

    @Transactional
    override fun cancelSubmission(bearerToken: String, userId: Long, deliveryId: Long): Delivery {
        val delivery = findDeliveryService.findOrThrow(deliveryId)
        validateDeliveryService.validateStatusAndRequester(delivery, DeliveryStatus.SUBMITTED, userId)
        delivery.status = DeliveryStatus.DRAFT
        val updatedDelivery = deliveryRepository.update(delivery)
        registerAuditLog(bearerToken, deliveryId, AuditLogMessage.CANCEL_SUBMIT, DeliveryAuditLogEvent.CANCEL_SUBMIT)
        return updatedDelivery
    }

    @Transactional
    override fun startAnalysis(bearerToken: String, userId: Long, deliveryId: Long): Delivery {
        val delivery = findDeliveryService.findOrThrow(deliveryId)
        validateDeliveryService.validateStatus(delivery, DeliveryStatus.SUBMITTED)
        delivery.reviewerId = userId
        delivery.status = DeliveryStatus.IN_ANALYSIS
        val updatedDelivery = deliveryRepository.update(delivery)
        registerAuditLog(bearerToken, deliveryId, AuditLogMessage.START_ANALYSIS, DeliveryAuditLogEvent.START_ANALYSIS)
        return updatedDelivery
    }

    @Transactional
    override fun approve(bearerToken: String, userId: Long, deliveryId: Long): Delivery {
        val delivery = findDeliveryService.findOrThrow(deliveryId)
        validateDeliveryService.validateStatusAndReviewer(delivery, DeliveryStatus.IN_ANALYSIS, userId)
        delivery.status = DeliveryStatus.APPROVED
        val updatedDelivery = deliveryRepository.update(delivery)
        registerAuditLog(bearerToken, deliveryId, AuditLogMessage.APPROVE, DeliveryAuditLogEvent.APPROVE)
        return updatedDelivery
    }

    @Transactional
    override fun reject(bearerToken: String, userId: Long, deliveryId: Long): Delivery {
        val delivery = findDeliveryService.findOrThrow(deliveryId)
        validateDeliveryService.validateStatusAndReviewer(delivery, DeliveryStatus.IN_ANALYSIS, userId)
        delivery.status = DeliveryStatus.REJECTED
        val updatedDelivery = deliveryRepository.update(delivery)
        registerAuditLog(bearerToken, deliveryId, AuditLogMessage.REJECT, DeliveryAuditLogEvent.REJECT)
        return updatedDelivery
    }

    @Transactional
    override fun requestChanges(
        bearerToken: String,
        userId: Long,
        deliveryId: Long,
        request: RequestDeliveryChangesRequest
    ): Delivery {
        val delivery = findDeliveryService.findOrThrow(deliveryId)
        validateDeliveryService.validateStatusAndReviewer(delivery, DeliveryStatus.IN_ANALYSIS, userId)
        delivery.status = DeliveryStatus.CHANGES_REQUESTED
        val updatedDelivery = deliveryRepository.update(delivery)
        registerAuditLog(bearerToken, deliveryId, request.description, DeliveryAuditLogEvent.REQUEST_CHANGES)
        return updatedDelivery
    }

    private fun registerAuditLog(
        bearerToken: String,
        deliveryId: Long,
        description: String,
        event: DeliveryAuditLogEvent,
    ) = deliveryAuditLogClient.register(
        bearerToken,
        RegisterDeliveryAuditLogRequest(
            deliveryId = deliveryId,
            event = event,
            description = description
        )
    )

}