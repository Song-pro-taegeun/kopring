package com.kopring_back.kopring.service.messagePubSub

import com.kopring_back.kopring.dto.MessageDto
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.stereotype.Service

/**
 *  메시지 발행 서비스
 * @author tgSong
 */
@Service
class RedisPublisher (
    private val redisTemplate: RedisTemplate<String, Any>
){

    /**
     * Object publish
     */
    fun publish(topic: ChannelTopic, data: Any) {
        when(data){
            is String -> redisTemplate.convertAndSend(topic.topic, data)
            is MessageDto -> redisTemplate.convertAndSend(topic.topic, data)
            else -> throw RuntimeException("Unsupported data type: ${data::class.java}")
        }
    }
}