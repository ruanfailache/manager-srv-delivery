package com.manager.api.services.impl

import com.manager.api.clients.DeliveryAuditLogClient
import com.manager.api.domain.constants.AuditLogMessage
import com.manager.api.domain.enums.DeliveryAuditLogEvent
import com.manager.api.domain.enums.DeliveryStatus
import com.manager.api.domain.repositories.DeliveryRepository
import com.manager.api.services.ApproveDeliveryService
import com.manager.api.services.SubmitDeliveryService
import com.manager.api.services.ValidateDeliveryDraftService
import com.manager.api.services.abstractions.AbstractChangeDeliveryStatusService
import jakarta.inject.Singleton

@Singleton
class ApproveDeliveryServiceImpl(
    deliveryAuditLogClient: DeliveryAuditLogClient,
    deliveryRepository: DeliveryRepository,
    validateDeliveryDraftService: ValidateDeliveryDraftService
) : ApproveDeliveryService,
    AbstractChangeDeliveryStatusService(
        DeliveryStatus.APPROVED,
        DeliveryAuditLogEvent.APPROVE,
        AuditLogMessage.APPROVE_DELIVERY,
        deliveryAuditLogClient,
        deliveryRepository,
        validateDeliveryDraftService
    )