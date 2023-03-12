package com.colabear754.jpa_example.repository.member

import com.colabear754.jpa_example.entity.member.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
    fun findByName(username: String): Member?
}