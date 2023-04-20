package com.colabear754.jpa_example.services.member.order.delivery

import com.colabear754.jpa_example.TestContainer
import com.colabear754.jpa_example.common.DeliveryStatus
import com.colabear754.jpa_example.domain.entities.member.order.Order
import com.colabear754.jpa_example.domain.entities.member.order.delivery.Delivery
import com.colabear754.jpa_example.domain.value.Address
import com.colabear754.jpa_example.dto.member.order.delivery.DeliveryRequest
import com.colabear754.jpa_example.dto.member.order.delivery.DeliveryStatusRequest
import com.colabear754.jpa_example.repositories.member.order.OrderRepository
import com.colabear754.jpa_example.repositories.member.order.delivery.DeliveryRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@TestContainer
class DeliveryServiceTest @Autowired constructor(
    private val deliveryService: DeliveryService,
    private val deliveryRepository: DeliveryRepository,
    private val orderRepository: OrderRepository
) {
   @AfterEach
    fun clear() {
         deliveryRepository.deleteAll()
    }

    @Test
    fun 배송준비() {
        // given
        val order = orderRepository.save(Order(null))
        val request = DeliveryRequest("12345", "서울시 강남구", "상세 주소", order.id!!)
        // when
        deliveryService.assignDelivery(request, "test")
        // then
        val savedDelivery = deliveryRepository.findAll()
        assertThat(savedDelivery).hasSize(1)
        assertThat(savedDelivery[0].destination).isEqualTo(Address("12345", "서울시 강남구", "상세 주소"))
        assertThat(savedDelivery[0].order!!.id).isEqualTo(order.id!!)
        assertThat(savedDelivery[0].status).isEqualTo(DeliveryStatus.READY)
    }

    @Test
    fun 배송상태변경() {
        // given
        val savedDelivery = deliveryRepository.save(Delivery(Address("12345", "서울시 강남구", "상세 주소"), Order(null)))
        val request = DeliveryStatusRequest(savedDelivery.id!!, DeliveryStatus.DELIVERY)
        // when
        deliveryService.updateDeliveryStatus(request, "test")
        // then
        val updatedDelivery = deliveryRepository.findAll()
        assertThat(updatedDelivery).hasSize(1)
        assertThat(updatedDelivery[0].status).isEqualTo(DeliveryStatus.DELIVERY)
    }
}