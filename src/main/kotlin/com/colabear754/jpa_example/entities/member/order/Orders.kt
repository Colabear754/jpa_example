package com.colabear754.jpa_example.entities.member.order

import com.colabear754.jpa_example.common.OrderStatus
import com.colabear754.jpa_example.entities.member.Member
import jakarta.persistence.*
import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import java.time.LocalDate
import java.util.*

@Entity
class Orders(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID?,
    @ManyToOne
    @JoinColumn(name = "member_id")
    @Cascade(CascadeType.DELETE)
    val member: Member,
    @Column(nullable = false)
    val orderDate: LocalDate,
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var status: OrderStatus
) {
    init {
        member.orders.add(this)
    }

    fun cancel() {
        if (status == OrderStatus.ORDER) {
            status = OrderStatus.CANCEL
        }
    }
}