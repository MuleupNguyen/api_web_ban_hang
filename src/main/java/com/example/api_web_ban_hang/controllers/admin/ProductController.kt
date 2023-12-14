package com.example.api_web_ban_hang.controllers.admin

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController {

    @RequestMapping("/hello")
    fun get(): String = "hello";
}