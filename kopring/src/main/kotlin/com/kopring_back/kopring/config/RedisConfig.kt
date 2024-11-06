package com.kopring_back.kopring.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer


@Configuration
class RedisConfig (
    @Value("\${spring.data.redis.host}")
    private val host: String,
    @Value("\${spring.data.redis.port}")
    private val port: Int
){
    // Lettuce와 Redis의 연결
    @Bean
    fun redisConnectionFactory(): LettuceConnectionFactory? {
        return LettuceConnectionFactory(RedisStandaloneConfiguration(host, port))
    }


    // RedisTemplate는 커넥션 위에서 조작 가능한 메소드 제공
    // 공식 문서에서는 <String, String>으로 되어 있지만
    // 출력값을 String으로 제한두지 않으려고 <String, Any>로 변경
    @Bean
    fun redisTemplate(connectionFactory: RedisConnectionFactory?): RedisTemplate<String, Any> {
        val redisTemplate = RedisTemplate<String, Any>()

        // setKeySerializer, setValueSerializer 사용 이유
        // RedisTemplate 사용 시에 Spring-Redis 간 데이터 직렬화, 역직렬화에 사용하는 방식이 Jdk 직렬화 방식
        // 직렬화 : 자바 시스템 내부에서 사용되는 Object 또는 Data를 외부의 자바 시스템에서도 사용할 수 있도록 byte 형태로 데이터를 변환하는 기술
        // 역직렬화 : byte로 변환된 Data를 원래대로 Object나 Data로 변환하는 기술
        // 직렬화/역직렬화 사용 이유
        // 복잡한 데이터 구조의 클래스의 객체라도 직렬화 기본 조건만 지키면 큰 작업 없이 바로 직렬화, 역직렬화가 가능
        // 데이터 타입이 자동으로 맞춰지기 때문에 관련 부분을 크게 신경 쓰지 않아도 됨
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.valueSerializer = GenericJackson2JsonRedisSerializer() // JSON 포맷으로 저장

        redisTemplate.connectionFactory = connectionFactory
        return redisTemplate
    }

    // 문자열에 특화한 메소드 제공
    @Bean
    fun stringRedisTemplate(redisConnectionFactory: RedisConnectionFactory?): StringRedisTemplate {
        val template = StringRedisTemplate()
        template.connectionFactory = redisConnectionFactory
        return template
    }
}