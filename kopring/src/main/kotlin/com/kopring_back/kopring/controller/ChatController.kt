package com.kopring_back.kopring.controller

import com.kopring_back.kopring.dto.MessageDto
import com.kopring_back.kopring.service.ChatService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/chat")
@CrossOrigin(origins = arrayOf("http://localhost:3000")) // 웹소켓 테스트 용 임시 코드
class ChatController(
        private val chatService: ChatService) {

    @PostMapping("/send")
    fun sendMessage(@RequestParam(required = true) channel: String,
                    @RequestBody message: MessageDto) {
        chatService.pubMsgChannel(channel, message)
    }

    @PostMapping("/cancle")
    fun cancelSubChannel(@RequestParam channel: String) {
        chatService.cancelSubChannel(channel)
    }
}