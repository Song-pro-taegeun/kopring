package com.kopring_back.kopring.controller

import com.kopring_back.kopring.dto.MessageDto
import com.kopring_back.kopring.service.ChatService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/chat")
class ChatController(
        private val chatService: ChatService) {

    @PostMapping("/send")
    fun sendMessage(@RequestBody message: MessageDto) {
        chatService.pubMsgChannel(message)
    }

    @GetMapping("/cancle")
    fun cancelSubChannel(@RequestParam channel: String) {
        chatService.cancelSubChannel(channel)
    }
}