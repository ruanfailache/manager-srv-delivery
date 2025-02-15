package com.manager.api.services

import com.manager.api.domain.requests.SaveDeliveryDraftRequest

interface CreateDeliveryDraftService {
    fun create(bearerToken: String, userId: Long, request: SaveDeliveryDraftRequest)
}