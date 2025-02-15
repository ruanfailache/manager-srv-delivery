package com.manager.api.domain.enums

enum class DeliveryAuditLogEvent {
    CREATE_DRAFT,
    UPDATE_DRAFT,
    SUBMIT_DRAFT,
    CANCEL_SUBMIT,
    START_ANALYSIS,
    APPROVE,
    REQUEST_CHANGES,
    REJECT,
}