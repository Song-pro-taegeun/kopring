package com.kopring_back.kopring.controller

import com.kopring_back.kopring.entity.Member
import com.kopring_back.kopring.repository.MemberRepositoryRedis
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/redis")
class RedisController (
    val memberRepositoryRedis: MemberRepositoryRedis
){
    @GetMapping("/members")
    fun getMembers() : MutableIterable<Member>{
        var users: MutableIterable<Member> = memberRepositoryRedis.findAll()
        return users
    }

    @PostMapping("/member")
    fun setMembers(@RequestBody member: Member) : Any{
        var result: Member = memberRepositoryRedis.save(member)
        return result
    }
}