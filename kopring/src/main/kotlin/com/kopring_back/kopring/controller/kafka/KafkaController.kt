package com.kopring_back.kopring.controller.kafka

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/kafka")
class KafkaController (private val kafkaProducerService: KafkaProducerService){
    @PostMapping("/send")
    fun sendMessageToKafka(@RequestParam message: String) {
        kafkaProducerService.sendMessage("topic1", message)
    }

    @PostMapping("/send/{chat}")
    fun sendMessageToBroker(
        @PathVariable chat:String, @RequestParam message:String) : String{
        // 브로커별로 다른 토픽을 설정
        val topic = "topic_$chat"
        kafkaProducerService.sendMessage(topic, message)
        return "Message sent to $chat on topic $topic"
    }
}