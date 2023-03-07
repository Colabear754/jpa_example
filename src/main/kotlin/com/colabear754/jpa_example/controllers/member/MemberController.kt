package com.colabear754.jpa_example.controllers.member

import com.colabear754.jpa_example.entity.member.Member
import com.colabear754.jpa_example.services.member.MemberService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/member")
class MemberController(private val memberService: MemberService) {
    @GetMapping
    fun getMembers() = memberService.getMembers()

    @PostMapping("/regist")
    fun insertMember(@RequestBody member: Member) = memberService.insertMember(member)

    @PutMapping("/update/{id}")
    fun updateMember(@PathVariable id: Long, @RequestBody newMember: Member) = memberService.updateMember(id, newMember)
}