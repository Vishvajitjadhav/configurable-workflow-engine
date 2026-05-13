package com.techpulse.workflow.controller;

import com.techpulse.workflow.dto.AuthResponse;
import com.techpulse.workflow.dto.LoginRequest;
import com.techpulse.workflow.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody LoginRequest request
    ) {

        String token = authService.login(request);

        return new AuthResponse(token);
    }
}