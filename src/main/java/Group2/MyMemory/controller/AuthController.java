package Group2.MyMemory.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import Group2.MyMemory.entity.User;
import Group2.MyMemory.repository.UserRepository;

@RestController
@RequestMapping("/api/register")
@AllArgsConstructor 
public class AuthController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping
    public ResponseEntity<String> register(@RequestParam String name,
                                           @RequestParam String email,
                                           @RequestParam String password) {

        if (userRepository.existsByEmail(email)) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        User user = User.builder()
                .name(name)
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }
}
