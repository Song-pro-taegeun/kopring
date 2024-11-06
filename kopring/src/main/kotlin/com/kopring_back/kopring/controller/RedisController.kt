package com.kopring_back.kopring.controller

import com.kopring_back.kopring.entity.Redis.Member
import com.kopring_back.kopring.repository.redis.MemberRepositoryRedis
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/redis")
class RedisController (
    val memberRepositoryRedis: MemberRepositoryRedis,
    val redisTemplate: RedisTemplate<String, Any>
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

    @GetMapping("/redisTemplate")
    fun setTemplateTest(): Any? {
        redisTemplate.opsForValue().set("test", "valueTest")
        return redisTemplate.opsForValue().get("test")
    }

}