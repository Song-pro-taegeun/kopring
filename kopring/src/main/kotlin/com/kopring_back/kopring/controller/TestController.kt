package com.kopring_back.kopring.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RequestMapping("/test")
@RestController
class TestController {

    @GetMapping("/testNum/{num}")
    fun getParam(@PathVariable("num") num:Int): Number {
        return 123 + num
    }
}