package com.manager.api.domain.repositories

import com.manager.api.domain.entities.Delivery
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import io.micronaut.data.repository.jpa.JpaSpecificationExecutor

@Repository
interface DeliveryRepository : JpaRepository<Delivery, Long>, JpaSpecificationExecutor<Delivery>
