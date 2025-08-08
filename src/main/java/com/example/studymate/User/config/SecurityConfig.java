package com.example.studymate.User.config;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;


@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
            .cors(cors -> cors.configurationSource(apiConfigurationSource()))
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers(
                            "/",
                            "/api/csrf",
                            "/api/page/**",
                            "/api/page/search-study",
                            "/api/auth/**",
                            "/api/users",
                            "/api/users/me",
                            "/api/studies",
                            "/api/studies/**"
                    ).permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/studies").authenticated()
                    .requestMatchers(
                            "/api/studies/*/join",
                            "/api/users/me/studies",
                            "/api/users/me/studies/**",
                            "/api/studies/{studyId}/records/",
                            "/api/studies/{studyId}/records/**",
                            "/api/records/**",
                            "/api/comments/**"
                    ).authenticated()
                    .anyRequest().denyAll()
            )
            .formLogin(login -> login
                    .loginProcessingUrl("/api/auth/login")
                    .successHandler((request, response, authentication) -> {
                        response.setStatus(HttpServletResponse.SC_OK);
                        response.setContentType("application/json");
                    })
                    .failureHandler((request, response, exception) -> {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        response.setContentType("application/json");
                    })
                    .permitAll())
            .logout(logout -> logout
                    .permitAll()
                    .logoutUrl("/api/auth/logout")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID", "XSRF-TOKEN")
                    .logoutSuccessHandler((request, response, authentication) -> {
                        response.setStatus(HttpServletResponse.SC_OK);
                    })
            );
        return http.build();
    }


    @Bean
    UrlBasedCorsConfigurationSource apiConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
