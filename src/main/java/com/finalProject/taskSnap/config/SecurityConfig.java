package com.finalProject.taskSnap.config;

import com.finalProject.taskSnap.handlers.CustomLogoutSuccessHandler;
import com.finalProject.taskSnap.handlers.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final GlobalExceptionHandler globalExceptionHandler;

    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthenticationFilter,
            AuthenticationProvider authenticationProvider,
            GlobalExceptionHandler globalExceptionHandler
    ) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.globalExceptionHandler = globalExceptionHandler;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, GlobalExceptionHandler globalExceptionHandler) throws Exception {
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/auth/login", "/auth/signup").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout
                                .logoutUrl("/auth/logout")
                                .logoutSuccessHandler(new CustomLogoutSuccessHandler())
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            ProblemDetail errorDetail = globalExceptionHandler.handleSecurityException(accessDeniedException);
                            response.setStatus(errorDetail.getStatus());
                            response.setContentType("application/json");
                            String json = String.format(
                                    "{\"type\":\"%s\",\"title\":\"%s\",\"status\":%d,\"detail\":\"%s\",\"instance\":%s,\"description\":\"%s\"}",
                                    "about:blank",
                                    errorDetail.getTitle(),
                                    errorDetail.getStatus(),
                                    errorDetail.getDetail(),
                                    errorDetail.getInstance() == null ? "null" : "\"" + errorDetail.getInstance() + "\"",
                                    errorDetail.getProperties().get("description")
                            );
                            response.getWriter().write(json);
                        })
                        .authenticationEntryPoint((request, response, authException) -> {
                            ProblemDetail errorDetail = globalExceptionHandler.handleSecurityException(authException);
                            response.setStatus(errorDetail.getStatus());
                            response.setContentType("application/json");
                            String json = String.format(
                                    "{\"type\":\"%s\",\"title\":\"%s\",\"status\":%d,\"detail\":\"%s\",\"instance\":%s,\"description\":\"%s\"}",
                                    "about:blank",
                                    errorDetail.getTitle(),
                                    errorDetail.getStatus(),
                                    errorDetail.getDetail(),
                                    errorDetail.getInstance() == null ? "null" : "\"" + errorDetail.getInstance() + "\"",
                                    errorDetail.getProperties().get("description")
                            );
                            response.getWriter().write(json);
                        })
                );
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}