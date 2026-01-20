package Group2.MyMemory.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Group2.MyMemory.dto.loginRequest;
import Group2.MyMemory.dto.loginResponse;
import Group2.MyMemory.dto.registerRequest;
import Group2.MyMemory.entity.User;
import Group2.MyMemory.repository.UserRepository;
import Group2.MyMemory.security.JwtUtil;



@Service
public class AuthService {


	private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // Constructor injection
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // Find user by username
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

	public User findByEmailUser(String email) {
		return userRepository.findByEmail(email);
	}

    // Register a new user
    public User register(registerRequest request) {
        // Check if username already exists
        if (findUserByUsername(request.getUsername()) != null) {
            throw new IllegalArgumentException("Username already exists");
        }
		if (findByEmailUser(request.getEmail()) != null) {
			throw new IllegalArgumentException("Email already exists");
		}

        // Encode the password
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // Create new user entity
        User user = new User();
        user.setUsername(request.getUsername());
		user.setEmail(request.getEmail());
        user.setPassword(encodedPassword);

        // Save to database
        return userRepository.save(user);
    }

    // login
    public loginResponse login(loginRequest request) {

        User user = userRepository.findByEmail(request.getEmail());
        if (user == null) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        // Check password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getId(),user.getEmail());

        return new loginResponse(
            token,
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getPassword()
        );
    };
   
}
