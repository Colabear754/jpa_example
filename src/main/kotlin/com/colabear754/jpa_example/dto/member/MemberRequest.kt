package com.colabear754.jpa_example.dto.member

data class MemberRequest(
    val name: String,
    val age: Int?,
    val zipCode: String,
    val address: String,
    val detailAddress: String,
    val phoneNumber: String,
    val requestor: String
)