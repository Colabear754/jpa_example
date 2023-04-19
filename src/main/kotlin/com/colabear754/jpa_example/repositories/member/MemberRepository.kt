package com.colabear754.jpa_example.repositories.member

import com.colabear754.jpa_example.domain.entities.member.Member
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MemberRepository : JpaRepository<Member, UUID>