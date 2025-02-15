package com.manager.api.services

import com.manager.api.domain.requests.SaveDeliveryDraftRequest

interface CreateDeliveryDraftService {
    fun create(userId: Long, request: SaveDeliveryDraftRequest)
}