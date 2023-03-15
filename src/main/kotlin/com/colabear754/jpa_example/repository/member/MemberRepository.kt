package com.colabear754.jpa_example.repository.member

import com.colabear754.jpa_example.entity.member.Member
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface MemberRepository : JpaRepository<Member, UUID>