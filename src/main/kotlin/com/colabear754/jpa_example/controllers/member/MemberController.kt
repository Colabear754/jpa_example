package com.colabear754.jpa_example.controllers.member

import com.colabear754.jpa_example.dto.member.MemberRequest
import com.colabear754.jpa_example.services.member.MemberService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/member")
class MemberController(private val memberService: MemberService) {
    @GetMapping
    fun getMembers() = memberService.getMembers()

    @PostMapping("/regist")
    fun insertMember(@RequestBody request: MemberRequest) = memberService.insertMember(request)

    @PutMapping("/update/{id}")
    fun updateMember(@PathVariable id: UUID, @RequestBody request: MemberRequest) = memberService.updateMember(id, request)

    @DeleteMapping("/delete/{id}")
    fun deleteMember(@PathVariable id: UUID) = memberService.deleteMember(id)
}