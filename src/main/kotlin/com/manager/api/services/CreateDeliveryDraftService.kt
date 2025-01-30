package com.manager.api.services

import com.manager.api.domain.requests.SaveDeliveryDraftRequest

interface CreateDeliveryDraftService {
    fun execute(userId: Long, request: SaveDeliveryDraftRequest)
}