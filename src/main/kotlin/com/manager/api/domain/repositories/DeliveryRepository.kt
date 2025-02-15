package com.manager.api.domain.repositories

import com.manager.api.domain.entities.Delivery
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface DeliveryRepository : JpaRepository<Delivery, Long>
