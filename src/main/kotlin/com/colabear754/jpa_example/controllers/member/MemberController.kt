package com.colabear754.jpa_example.controllers.member

import com.colabear754.jpa_example.entity.member.Member
import com.colabear754.jpa_example.services.member.MemberService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class MemberController(private val memberService: MemberService) {
    @PostMapping("/member")
    fun insertMember(@RequestBody member: Member) = memberService.insertMember(member)
}