package com.colabear754.jpa_example.domain.value

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class Address(
    @Column(length = 10)
    val zipCode: String,
    val address: String,
    val detailAddress: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return zipCode == (other as Address).zipCode && address == other.address && detailAddress == other.detailAddress
    }

    override fun hashCode() = arrayOf(zipCode, address, detailAddress).contentHashCode()
}
