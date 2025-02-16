package com.manager.api.domain.specifications

import com.manager.api.domain.entities.Delivery
import com.manager.api.domain.enums.DeliveryStatus
import com.manager.api.domain.requests.FindAllDeliveriesRequest
import io.micronaut.data.repository.jpa.criteria.PredicateSpecification
import jakarta.persistence.criteria.Predicate

object DeliverySpecification {
    private fun titleLike(title: String) = PredicateSpecification<Delivery> { root, criteriaBuilder ->
        criteriaBuilder.like(root.get("title"), "%$title%")
    }

    private fun statusEquals(status: DeliveryStatus) = PredicateSpecification<Delivery> { root, criteriaBuilder ->
        criteriaBuilder.equal(root.get<DeliveryStatus>("status"), status)
    }

    private fun requesterIdEquals(requesterId: Long) = PredicateSpecification<Delivery> { root, criteriaBuilder ->
        criteriaBuilder.equal(root.get<Long>("requesterId"), requesterId)
    }

    private fun reviewerIdEquals(reviewerId: Long) = PredicateSpecification<Delivery> { root, criteriaBuilder ->
        criteriaBuilder.equal(root.get<Long>("reviewerId"), reviewerId)
    }

    fun fromFindAllRequest(request: FindAllDeliveriesRequest) = PredicateSpecification<Delivery> { root, criteriaBuilder ->
        val predicates = mutableListOf<Predicate>()

        request.title?.let { title ->
            predicates.add(titleLike(title).toPredicate(root, criteriaBuilder))
        }

        request.status?.let { status ->
            predicates.add(statusEquals(status).toPredicate(root, criteriaBuilder))
        }

        request.requesterId?.let { requesterId ->
            predicates.add(requesterIdEquals(requesterId).toPredicate(root, criteriaBuilder))
        }

        request.reviewerId?.let { reviewerId ->
            predicates.add(reviewerIdEquals(reviewerId).toPredicate(root, criteriaBuilder))
        }

        criteriaBuilder.and(*predicates.toTypedArray())
    }
}