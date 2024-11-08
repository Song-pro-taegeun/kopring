package com.kopring_back.kopring.entity.redisEntity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash("chat")
class ChatRoom (
    @Id
    val roomId: String ,
    val participants: List<String>
)