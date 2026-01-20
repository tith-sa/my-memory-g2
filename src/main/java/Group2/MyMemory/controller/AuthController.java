package Group2.MyMemory.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RestController;

import Group2.MyMemory.dto.loginRequest;
import Group2.MyMemory.dto.loginResponse;
import Group2.MyMemory.dto.registerRequest;
import Group2.MyMemory.dto.registerResponse;
import Group2.MyMemory.service.AuthService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    

    @PostMapping("/register")
    public ResponseEntity<registerResponse> registerUser( @RequestBody registerRequest request) {
        try {
            registerResponse response = authService.register(request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<loginResponse> loginUser(@RequestBody loginRequest request) {
        try {
            loginResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
}