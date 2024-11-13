package com.kopring_back.kopring.controller

import com.google.gson.Gson
import com.kopring_back.kopring.controller.redis.RedisChatController
import com.kopring_back.kopring.dto.RoomDto
import com.kopring_back.kopring.service.RedisChatService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.whenever
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ExtendWith(MockitoExtension::class)
class RedisChatControllerTest {

    @InjectMocks
    private lateinit var redisChatController: RedisChatController

    @Mock
    private lateinit var redisChatService: RedisChatService
    private lateinit var mockMvc: MockMvc
    private lateinit var testg: RoomDto

    private val gson = Gson()
    private lateinit var roomDtoJson: String

    @BeforeEach
    fun init() {
        val roomDto = roomDto("TestName", "TestHost")
        roomDtoJson = gson.toJson(roomDto)
        testg = roomDto
        mockMvc = MockMvcBuilders.standaloneSetup(redisChatController).build()
    }

    @DisplayName("방생성")
    @Test
    fun createRoomProcess() {
        whenever(redisChatService.createRoom(testg)).thenReturn(testg)

        val resultAction: ResultActions = mockMvc.perform(
            MockMvcRequestBuilders.post("/chat/room")
                .contentType(MediaType.APPLICATION_JSON)
                .content(roomDtoJson)
        )

        resultAction.andExpect(status().isOk)
        val content = resultAction.andReturn().response.contentAsString
        println("방 생성: $content")
    }

    @DisplayName("방 조회")
    @Test
    fun getRoomListProcess() {
        val roomList = listOf(testg)
        whenever(redisChatService.getRoomList()).thenReturn(roomList)

        val resultAction: ResultActions = mockMvc.perform(
            MockMvcRequestBuilders.get("/chat/rooms")
        )

        resultAction.andExpect(status().isOk)
    }

    private fun roomDto(roomName: String, host: String): RoomDto {
        return RoomDto(
            roomName = roomName,
            host = host
        )
    }
}
