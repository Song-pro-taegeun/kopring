package com.kopring_back.kopring.service.messagePubSub

import com.fasterxml.jackson.databind.ObjectMapper
import com.kopring_back.kopring.dto.MessageDto
import org.slf4j.LoggerFactory
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service

@Service
class RedisSubscribeListener(
    private val template: RedisTemplate<String, Any>,
    private val objectMapper: ObjectMapper,
    private val messagingTemplate: SimpMessagingTemplate
) : MessageListener{
    private val log = LoggerFactory.getLogger(RedisSubscribeListener::class.java)

    override fun onMessage(message: Message, pattern: ByteArray?) {
        try {
            val publishMessage = template.stringSerializer.deserialize(message.body)
            val messageDto = objectMapper.readValue(publishMessage, MessageDto::class.java)

            // WebSocket을 통해 클라이언트로 메시지 전송
            if (publishMessage != null) {
                log.info("Redis Subscribe Channel : ${messageDto.roomId}")
                log.info("Redis SUB Message : {}", publishMessage)

                messagingTemplate.convertAndSend("/topic/chat", publishMessage)
            }
        } catch (e: Exception) {
            log.error(e.message, e)
        }
    }
}