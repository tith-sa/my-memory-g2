package Group2.MyMemory.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RestController;
import Group2.MyMemory.dto.registerRequest;
import Group2.MyMemory.dto.registerResponse;
import Group2.MyMemory.entity.User;
import Group2.MyMemory.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<registerResponse> registerUser( @RequestBody registerRequest request) {
        try {
            User newUser = authService.register(request);
             registerResponse response = new registerResponse(
            newUser.getId(),
            newUser.getUsername(),
            newUser.getEmail(),
            newUser.getPassword()
        );
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}