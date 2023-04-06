package com.colabear754.jpa_example.controllers.member.order

import com.colabear754.jpa_example.dto.member.order.OrderItemRequest
import com.colabear754.jpa_example.entities.member.order.Order
import com.colabear754.jpa_example.repositories.member.order.OrderItemRepository
import com.colabear754.jpa_example.services.member.MemberService
import com.colabear754.jpa_example.services.member.order.OrderService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/member/order")
class OrderController(
    private val orderService: OrderService,
    private val memberService: MemberService,
    private val orderItemRepository: OrderItemRepository
) {
    @GetMapping("/list")
    fun list(memberId: UUID) = run {
        val member = memberService.getMember(memberId)
        orderService.getOrders(member)
    }

    @PostMapping("/new")
    fun newOrder(memberId: UUID, orderItems: List<OrderItemRequest>) = run {
        val member = memberService.getMember(memberId)
        orderService.newOrder(Order(member), orderItems)
    }
}