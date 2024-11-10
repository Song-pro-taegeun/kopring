package com.kopring_back.kopring.controller.kafka

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class KafkaConsumerService {

//    @KafkaListener(topics = ["topic1"], groupId = "my-consumer-group")
//    fun consume(record: ConsumerRecord<String, String>) {
//        println("Consumed message: '${record.value()}' from topic: ${record.topic()}")
//    }
}
