package com.kopring_back.kopring.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

data class RoomDto @JsonCreator constructor(
    @JsonProperty("roomId")
    val roomId: UUID = UUID.randomUUID(),

    @JsonProperty("roomName")
    val roomName: String,

    @JsonProperty("host")
    val host: String,

    @JsonProperty("participants")
    val participants: MutableList<String> = mutableListOf(),

    @JsonProperty("createdDate")
    val createdDate: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
)
