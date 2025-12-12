package Group2.MyMemory.config; 

import Group2.MyMemory.security.JwtAuthenticationFilter; 
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        http
            // 1. Disable CSRF for stateless REST API (FIX for 403 error)
            .csrf(AbstractHttpConfigurer::disable)
            
            .authorizeHttpRequests(auth -> auth
                
                // 2. PUBLIC ENDPOINTS: Allow login/register without a token
                .requestMatchers("/api/auth/**", "/api/users/register").permitAll()
                
                // 3. PROTECTED ENDPOINTS: Requires a valid token
                .requestMatchers("/api/memories/**").authenticated() 
                
                .anyRequest().authenticated()
            )
            
            // 4. Set session policy to STATELESS
            .sessionManagement(sess -> sess
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            
            // 5. Register the custom authentication provider
            .authenticationProvider(authenticationProvider)
            
            // 6. Add the JWT filter before the standard filter 
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // 

        return http.build();
    }
}