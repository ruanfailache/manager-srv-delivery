package com.manager.api.services

import com.manager.api.domain.requests.SaveDeliveryDraftRequest

interface UpdateDeliveryDraftService {
    fun updateDraft(bearerToken: String, userId: Long, deliveryId: Long, request: SaveDeliveryDraftRequest)
}