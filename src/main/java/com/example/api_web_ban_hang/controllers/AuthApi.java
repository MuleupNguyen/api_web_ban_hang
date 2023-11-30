package com.example.api_web_ban_hang.controllers;
import javax.validation.Valid;

import com.example.api_web_ban_hang.jwts.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.api_web_ban_hang.models.AuthResponse;
import com.example.api_web_ban_hang.models.User;
import com.example.api_web_ban_hang.models.AuthRequest;
import com.example.api_web_ban_hang.repos.UserRepository;

import java.net.URI;
import java.time.LocalDateTime;


@RestController
public class AuthApi {
    @Autowired AuthenticationManager authManager;
    @Autowired
    JwtTokenUtil jwtUtil;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/api/authentication/login-user")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(), request.getPassword())
            );

            User user = (User) authentication.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(user);
            AuthResponse response = new AuthResponse(user.getUsername(), accessToken, "24 hours");

            return ResponseEntity.ok().body(response);

        } catch (BadCredentialsException ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @PostMapping("/api/register")
    public ResponseEntity<User> create(@RequestBody @Valid User user) {
        System.out.println(user);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        user.setTimeCreated(LocalDateTime.now());
        User saveUser = userRepository.save(user);
        return new ResponseEntity(saveUser, HttpStatus.OK);
    }
}