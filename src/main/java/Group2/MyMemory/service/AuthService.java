package Group2.MyMemory.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Group2.MyMemory.dto.registerRequest;
import Group2.MyMemory.entity.User;
import Group2.MyMemory.repository.UserRepository;



@Service
public class AuthService {


	private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor injection
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
   
}
