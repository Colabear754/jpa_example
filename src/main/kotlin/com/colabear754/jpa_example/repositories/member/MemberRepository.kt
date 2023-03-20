package com.colabear754.jpa_example.repositories.member

import com.colabear754.jpa_example.entities.member.Member
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface MemberRepository : JpaRepository<Member, UUID>