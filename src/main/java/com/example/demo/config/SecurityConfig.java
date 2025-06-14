package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // これないとログイン機能が動作しない
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/furukari", "/furukari/signin", "/furukari/signup").permitAll()
                        .anyRequest().authenticated())

                .formLogin(form -> form
                        .loginPage("/furukari/signin")
                        .defaultSuccessUrl("/furukari/item", true)
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/furukari/signin?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSION")
                        .permitAll());

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
