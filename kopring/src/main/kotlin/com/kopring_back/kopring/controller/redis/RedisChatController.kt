package com.kopring_back.kopring.controller.redis

import com.kopring_back.kopring.dto.RedisMessageDto
import com.kopring_back.kopring.dto.RoomDto
import com.kopring_back.kopring.service.RedisChatService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/chat")
class RedisChatController(
        private val chatService: RedisChatService) {
    @PostMapping("/room")
    fun createRoom(@RequestBody roomDto: RoomDto): RoomDto {
        return chatService.createRoom(roomDto)
    }

    @GetMapping("/rooms")
    fun getRoomList(): List<RoomDto>  {
        return chatService.getRoomList()
    }

    @PostMapping("/room/participant")
    fun addParticipant(
        @RequestParam roomId: String,
        @RequestParam participant: String
    ): RoomDto? {
        return chatService.addParticipantToRoom(roomId, participant)
    }

    @PostMapping("/send")
    fun sendMessage(@RequestBody message: RedisMessageDto) {
        chatService.pubMsgChannel(message)
    }

    @GetMapping("/cancle")
    fun cancelSubChannel(@RequestParam channel: String) {
        chatService.cancelSubChannel(channel)
    }
}