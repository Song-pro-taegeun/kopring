package com.kopring_back.kopring.service

import com.kopring_back.kopring.dto.MessageDto
import com.kopring_back.kopring.service.messagePubSub.RedisPublisher
import com.kopring_back.kopring.service.messagePubSub.RedisSubscribeListener
import org.jetbrains.annotations.TestOnly
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.stereotype.Service


@Service
class ChatService (
    private val redisSubscribeListener: RedisSubscribeListener,
    private val redisMessageListenerContainer: RedisMessageListenerContainer,
    private val redisPublisher: RedisPublisher,

){
    /**
     * Channel 별 Message 전송
     * @param
     */
    fun pubMsgChannel(channel: String, message: MessageDto) {
        //1. 요청한 Channel 을 구독.
        redisMessageListenerContainer.addMessageListener(redisSubscribeListener, ChannelTopic(channel))

        //2. Message 전송
        redisPublisher.publish(ChannelTopic(channel), message)
    }


    /**
     * Channel 구독 취소
     * @param channel
     */
    fun cancelSubChannel(channel: String?) {
        redisMessageListenerContainer.removeMessageListener(redisSubscribeListener)
    }
}