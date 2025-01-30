package com.manager.api.services.impl

import com.manager.api.domain.enums.DeliveryAuditLogEvent
import com.manager.api.domain.repositories.DeliveryRepository
import com.manager.api.domain.requests.SaveDeliveryDraftRequest
import com.manager.api.services.CreateDeliveryDraftService
import com.manager.api.services.DeliveryAuditLogService
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton

@Singleton
class CreateDeliveryDraftServiceImpl(
    private val deliveryAuditLogService: DeliveryAuditLogService,
    private val deliveryRepository: DeliveryRepository
) : CreateDeliveryDraftService {

    @Transactional
    override fun execute(userId: Long, request: SaveDeliveryDraftRequest) {
        val mappedEntity = request.createDelivery(userId)
        val savedEntity = deliveryRepository.save(mappedEntity)
        deliveryAuditLogService.log(savedEntity.id!!, userId, DeliveryAuditLogEvent.CREATE_DRAFT)
    }

}