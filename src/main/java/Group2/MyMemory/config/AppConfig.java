package Group2.MyMemory.config;

import Group2.MyMemory.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor // Required because we use final fields for injection
public class AppConfig {

    // Inject the UserRepository to fetch user details during authentication
    private final UserRepository userRepository; 

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findById(Long.parseLong(username))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + username));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        // ESSENTIAL for SecurityConfig: Links UserDetailsService and PasswordEncoder
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        // Provides the AuthenticationManager used by the AuthController for logging in
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Your original bean definition
        return new BCryptPasswordEncoder();
    }
}