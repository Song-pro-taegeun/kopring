package com.kopring_back.kopring.config

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig : WebSocketMessageBrokerConfigurer {

    override fun configureMessageBroker(config: MessageBrokerRegistry) {
        config.enableSimpleBroker("/topic") // 클라이언트가 구독할 경로
        config.setApplicationDestinationPrefixes("/app") // 클라이언트가 메시지를 전송할 경로
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/ws")
//            .setAllowedOrigins("*")
            .setAllowedOriginPatterns("http://localhost:3000")
            .withSockJS() // WebSocket 엔드포인트 설정
    }
}