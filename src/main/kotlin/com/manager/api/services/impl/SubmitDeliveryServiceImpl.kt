package com.manager.api.services.impl

import com.manager.api.clients.DeliveryAuditLogClient
import com.manager.api.domain.enums.DeliveryAuditLogEvent
import com.manager.api.domain.enums.DeliveryStatus
import com.manager.api.domain.repositories.DeliveryRepository
import com.manager.api.services.SubmitDeliveryService
import com.manager.api.services.ValidateDeliveryDraftService
import com.manager.api.services.abstractions.AbstractChangeDeliveryStatusService
import jakarta.inject.Singleton

@Singleton
class SubmitDeliveryServiceImpl(
    deliveryAuditLogClient: DeliveryAuditLogClient,
    deliveryRepository: DeliveryRepository,
    validateDeliveryDraftService: ValidateDeliveryDraftService
) : SubmitDeliveryService,
    AbstractChangeDeliveryStatusService(
        DeliveryStatus.SUBMITTED,
        DeliveryAuditLogEvent.SUBMIT,
        deliveryAuditLogClient,
        deliveryRepository,
        validateDeliveryDraftService
    )