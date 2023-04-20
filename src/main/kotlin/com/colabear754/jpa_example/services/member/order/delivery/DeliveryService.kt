package com.colabear754.jpa_example.services.member.order.delivery

import com.colabear754.jpa_example.domain.entities.member.order.delivery.Delivery
import com.colabear754.jpa_example.dto.member.order.delivery.DeliveryRequest
import com.colabear754.jpa_example.dto.member.order.delivery.DeliveryStatusRequest
import com.colabear754.jpa_example.repositories.member.order.OrderRepository
import com.colabear754.jpa_example.repositories.member.order.delivery.DeliveryRepository
import com.colabear754.jpa_example.util.findByIdOrThrow
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeliveryService(
    private val deliveryRepository: DeliveryRepository,
    private val orderRepository: OrderRepository
) {
    @Transactional
    fun assignDelivery(request: DeliveryRequest, requestor: String): Delivery {
        val order = orderRepository.findByIdOrThrow(request.orderId)
        val delivery = Delivery.from(request, requestor)
        order.assignDelivery(delivery)
        return deliveryRepository.save(delivery)
    }

    @Transactional
    fun updateDeliveryStatus(request: DeliveryStatusRequest, requestor: String): Delivery {
        val delivery = deliveryRepository.findByIdOrThrow(request.id)
        delivery.updateStatus(request.status, requestor)
        return delivery
    }
}