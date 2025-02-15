package com.manager.api.services.impl

import com.manager.api.clients.DeliveryAuditLogClient
import com.manager.api.domain.enums.DeliveryStatus
import com.manager.api.domain.repositories.DeliveryRepository
import com.manager.api.services.DeleteDeliveryDraftService
import com.manager.api.services.FindDeliveryService
import com.manager.api.services.ValidateDeliveryService
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton

@Singleton
class DeleteDeliveryDraftServiceImpl(
    private val deliveryAuditLogClient: DeliveryAuditLogClient,
    private val deliveryRepository: DeliveryRepository,
    private val findDeliveryService: FindDeliveryService,
    private val validateDeliveryService: ValidateDeliveryService
) : DeleteDeliveryDraftService {

    @Transactional
    override fun delete(bearerToken: String, userId: Long, deliveryId: Long) {
        val delivery = findDeliveryService.findOrThrow(deliveryId)
        validateDeliveryService.validateStatusAndRequester(delivery, DeliveryStatus.DRAFT, userId)
        deliveryRepository.delete(delivery)
        deliveryAuditLogClient.delete(bearerToken, deliveryId)
    }

}