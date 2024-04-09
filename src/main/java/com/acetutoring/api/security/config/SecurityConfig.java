package com.acetutoring.api.security.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .authorizeHttpRequests((authorize) -> {
                    authorize.requestMatchers(HttpMethod.GET, "/public/api/**").permitAll();
                    authorize.requestMatchers(HttpMethod.POST, "/public/api/**").permitAll();
                    authorize.requestMatchers(HttpMethod.GET, "/api/**").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.GET, "/student/api/**").hasRole("CUSTOMER");
                    authorize.requestMatchers(HttpMethod.POST, "/student/api/**").hasRole("CUSTOMER");
                    authorize.requestMatchers(HttpMethod.PUT, "/student/api/**").hasRole("CUSTOMER");
                    authorize.requestMatchers(HttpMethod.OPTIONS, "/student/api/**").hasRole("CUSTOMER");
                    authorize.anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }
}
