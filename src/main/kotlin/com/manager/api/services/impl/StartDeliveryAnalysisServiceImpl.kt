package com.manager.api.services.impl

import com.manager.api.clients.DeliveryAuditLogClient
import com.manager.api.domain.enums.DeliveryAuditLogEvent
import com.manager.api.domain.enums.DeliveryStatus
import com.manager.api.domain.repositories.DeliveryRepository
import com.manager.api.services.StartDeliveryAnalysisService
import com.manager.api.services.ValidateDeliveryDraftService
import com.manager.api.services.abstractions.AbstractChangeDeliveryStatusService
import jakarta.inject.Singleton

@Singleton
class StartDeliveryAnalysisServiceImpl(
    deliveryAuditLogClient: DeliveryAuditLogClient,
    deliveryRepository: DeliveryRepository,
    validateDeliveryDraftService: ValidateDeliveryDraftService
) : StartDeliveryAnalysisService,
    AbstractChangeDeliveryStatusService(
        DeliveryStatus.IN_ANALYSIS,
        DeliveryAuditLogEvent.START_ANALYSIS,
        deliveryAuditLogClient,
        deliveryRepository,
        validateDeliveryDraftService
    )