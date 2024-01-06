package com.example.api_web_ban_hang.controllers.admin

import com.example.api_web_ban_hang.repos.UserRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userRepository: UserRepository) {
    @GetMapping("/api/admin/users-amount")
    fun getUsers(): Long = userRepository.count()
}