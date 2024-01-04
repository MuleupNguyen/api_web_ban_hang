package com.example.api_web_ban_hang.controllers;

import com.example.api_web_ban_hang.jwts.JwtTokenUtil;
import com.example.api_web_ban_hang.models.AuthRequest;
import com.example.api_web_ban_hang.models.AuthResponse;
import com.example.api_web_ban_hang.models.ResponseObject;
import com.example.api_web_ban_hang.models.entities.User;
import com.example.api_web_ban_hang.models.request.AuthRegister;
import com.example.api_web_ban_hang.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;


@RestController
@CrossOrigin("*")
public class AuthApi {
    @Autowired
    AuthenticationManager authManager;
    @Autowired
    JwtTokenUtil jwtUtil;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/api/authentication/login-user")
    public ResponseEntity<ResponseObject> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(), request.getPassword())
            );

            User user = (User) authentication.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(user);
            AuthResponse response = new AuthResponse(user.getUsername(), accessToken, "24 hours");

            return ResponseEntity.ok().body(new ResponseObject("OK", "Đăng nhập thành công", response));

        } catch (BadCredentialsException ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.ok().body(new ResponseObject("Faild", "Đăng nhập thất bại", ""));
        }
    }

    @PostMapping("/api/register")
    public ResponseObject create(@RequestBody @Valid AuthRegister request) {
        User user = new User();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(request.getPassword());
        user.setFullname(request.getFullName());
        user.setUsername(request.getUsername());
        user.setPassword(password);
        user.setTimeCreated(LocalDateTime.now());
        User saveUser = userRepository.save(user);
        return Optional.of(new ResponseObject("OK","Đăng kí thành công",saveUser))
                .orElse(new ResponseObject("Faild","Đăng kí thất bại",null));
    }
}