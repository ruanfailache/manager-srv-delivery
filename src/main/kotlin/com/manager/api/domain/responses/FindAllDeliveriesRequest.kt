package com.manager.api.domain.responses

import com.manager.api.domain.enums.DeliveryStatus

data class FindAllDeliveriesRequest(
    val title: String?,
    val status: DeliveryStatus?,
    val requesterId: Long?,
    val reviewerId: Long?
)