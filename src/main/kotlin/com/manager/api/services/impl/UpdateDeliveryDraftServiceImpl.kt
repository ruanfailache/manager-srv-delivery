package com.manager.api.services.impl

import com.manager.api.domain.enums.DeliveryAuditLogEvent
import com.manager.api.domain.repositories.DeliveryRepository
import com.manager.api.domain.requests.SaveDeliveryDraftRequest
import com.manager.api.services.DeliveryAuditLogService
import com.manager.api.services.UpdateDeliveryDraftService
import com.manager.api.services.ValidateDeliveryDraftService
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton

@Singleton
class UpdateDeliveryDraftServiceImpl(
    private val deliveryAuditLogService: DeliveryAuditLogService,
    private val deliveryRepository: DeliveryRepository,
    private val validateDeliveryDraftService: ValidateDeliveryDraftService
) : UpdateDeliveryDraftService {

    @Transactional
    override fun execute(userId: Long, deliveryId: Long, request: SaveDeliveryDraftRequest) {
        val foundDelivery = validateDeliveryDraftService.execute(deliveryId)
        val mappedDelivery = request.updateDelivery(foundDelivery)
        deliveryRepository.update(mappedDelivery)
        deliveryAuditLogService.log(deliveryId, userId, DeliveryAuditLogEvent.UPDATE_DRAFT)
    }

}