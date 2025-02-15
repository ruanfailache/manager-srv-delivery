package com.manager.api.services

import com.manager.api.domain.requests.SaveDeliveryDraftRequest

interface UpdateDeliveryDraftService {
    fun updateDraft(userId: Long, deliveryId: Long, request: SaveDeliveryDraftRequest)
}