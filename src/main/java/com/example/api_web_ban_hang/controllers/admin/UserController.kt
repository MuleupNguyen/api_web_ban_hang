package com.example.api_web_ban_hang.controllers.admin

import com.example.api_web_ban_hang.mapper.admin.UserDTO
import com.example.api_web_ban_hang.mapper.admin.toUserDTO
import com.example.api_web_ban_hang.repos.UserRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userRepository: UserRepository
) {

    @GetMapping("/api/admin/users")
    fun getUsers(): List<UserDTO> = userRepository.findAll().map { it.toUserDTO() }
}