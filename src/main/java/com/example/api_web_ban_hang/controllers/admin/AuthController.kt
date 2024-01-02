package com.example.api_web_ban_hang.controllers.admin

import com.example.api_web_ban_hang.models.entities.Admin
import com.example.api_web_ban_hang.repos.AdminRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime

@RestController
class AuthController {

    @Autowired
    private lateinit var adminRepository: AdminRepository

    @PostMapping("/api/admin/sign_up")
    fun signUp(@RequestBody admin: Admin): Admin {
        if (adminRepository.findAll().any { it.email == admin.email }) throw ResponseStatusException(HttpStatus.CONFLICT, "Email already exists.")
        return adminRepository.save(admin.apply {
            timeCreated = LocalDateTime.now()
        })
    }

    @PostMapping("/api/admin/sign_in")
    fun signIn(@RequestBody admin: Admin): Admin {
        val savedAdmin = adminRepository.findAll().find { it.email == admin.email && it.password == admin.password }
        if (savedAdmin == null) throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password is incorrect.")
        return savedAdmin
    }
}