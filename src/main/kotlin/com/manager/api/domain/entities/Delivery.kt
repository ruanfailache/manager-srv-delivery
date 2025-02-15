package com.manager.api.domain.entities

import com.manager.api.domain.enums.DeliveryStatus
import com.manager.api.domain.exceptions.InvalidDeliveryStatusException
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "entregas")
data class Delivery(
    @field:Id
    @field:GeneratedValue
    var id: Long? = null,

    @field:Column(name = "titulo")
    var title: String,

    @field:Column(name = "descricao")
    var description: String,

    @field:Enumerated(EnumType.STRING)
    @field:Column(name = "situacao")
    var status: DeliveryStatus,

    @field:Column(name = "solicitante_id")
    var requesterId: Long,

    @field:Column(name = "revisor_id", nullable = true)
    var reviewerId: Long? = null,

    @field:CreationTimestamp
    @field:Column(name = "data_criacao")
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @field:UpdateTimestamp
    @field:Column(name = "data_atualizacao")
    var updatedAt: LocalDateTime = LocalDateTime.now(),
)