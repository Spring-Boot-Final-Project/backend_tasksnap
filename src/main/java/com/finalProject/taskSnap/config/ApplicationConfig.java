package com.finalProject.taskSnap.config;
import com.finalProject.taskSnap.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ApplicationConfig {
    private final UserRepository userRepository;

    public ApplicationConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    Returns a new instance of UserDetailsService
    @Bean
    UserDetailsService userDetailsService() {
        return username -> userRepository.findTaskSnapUsersByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

//    Returns a new instance of BCryptPasswordEncoder
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    Returns a new instance of AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Creates and configures an AuthenticationProvider bean.
     *
     * This method sets up a DaoAuthenticationProvider with a custom UserDetailsService
     * and a BCryptPasswordEncoder. The DaoAuthenticationProvider is responsible for
     * retrieving user details and validating credentials during authentication.
     *
     * @return an instance of AuthenticationProvider configured with a UserDetailsService
     *         and a BCryptPasswordEncoder.
     */
    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
}