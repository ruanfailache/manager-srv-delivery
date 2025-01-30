package com.manager.api.domain.requests

import com.manager.api.domain.entities.Delivery
import com.manager.api.domain.enums.DeliveryStatus

data class SaveDeliveryDraftRequest(
    val title: String,
    val description: String,
) {

    fun createDelivery(userId: Long) = Delivery(
        title = title,
        description = description,
        requesterId = userId,
        status = DeliveryStatus.DRAFT,
    )

    fun updateDelivery(delivery: Delivery) = delivery.copy(
        title = title,
        description = description,
    )

}