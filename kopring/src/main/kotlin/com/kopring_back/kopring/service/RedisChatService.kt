package com.kopring_back.kopring.service

import com.kopring_back.kopring.dto.RedisMessageDto
import com.kopring_back.kopring.dto.RoomDto
import com.kopring_back.kopring.service.messagePubSub.RedisPublisher
import com.kopring_back.kopring.service.messagePubSub.RedisSubscribeListener
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ValueOperations
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.stereotype.Service


@Service
class RedisChatService (
    private val redisSubscribeListener: RedisSubscribeListener,
    private val redisMessageListenerContainer: RedisMessageListenerContainer,
    private val redisPublisher: RedisPublisher,
    private val roomDtoRedisTemplate: RedisTemplate<String, RoomDto>
){
    private val roomKeyPrefix = "chatRoom:"

    /**
     * chat room 만들기
     * @param
     */
    fun createRoom(roomDto: RoomDto): RoomDto {
        val roomKey = roomKeyPrefix + roomDto.roomId
        roomDtoRedisTemplate.opsForValue().set(roomKey, roomDto)
        return roomDtoRedisTemplate.opsForValue().get(roomKey) ?: roomDto
    }

    /**
     * chat room 리스트 조회
     * @param
     */
    fun getRoomList(): List<RoomDto> {
        val keys = roomDtoRedisTemplate.keys("$roomKeyPrefix*") ?: emptySet()
        return keys.mapNotNull { key ->
            roomDtoRedisTemplate.opsForValue().get(key)
        }
    }

    /**
     * chat room 참가자 추가
     * @param
     */
    fun addParticipantToRoom(roomId: String, participant: String): RoomDto? {
        val roomKey = roomKeyPrefix + roomId
        val values: ValueOperations<String, RoomDto> = roomDtoRedisTemplate.opsForValue() // roomDtoRedisTemplate 사용

        val roomDto = values.get(roomKey) // Redis에서 RoomDto 가져오기
        roomDto?.let {
            if (!it.participants.contains(participant)) {
                it.participants.add(participant) // 참가자 추가
                values.set(roomKey, it) // 업데이트된 roomDto 저장
            }
        }
        return roomDto // 업데이트된 RoomDto 반환
    }

    /**
     * Channel 별 Message 전송
     * @param
     */
    fun pubMsgChannel(message: RedisMessageDto) {
        //1. 요청한 Channel 을 구독.
        redisMessageListenerContainer.addMessageListener(redisSubscribeListener, ChannelTopic(message.roomId))

        //2. Message 전송
        redisPublisher.publish(ChannelTopic(message.roomId), message)
    }

    /**
     * Channel 구독 취소
     * @param channel
     */
    fun cancelSubChannel(channel: String?) {
        redisMessageListenerContainer.removeMessageListener(redisSubscribeListener)
    }
}