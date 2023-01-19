package com.example.BankManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {


    @Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        /*
        * Default configuration of spring security
        */
        http.authorizeRequests().anyRequest().authenticated()
        .and().formLogin()
        .and().httpBasic();

        /*
         * disable authentication
         */
        // http.authorizeRequests().anyRequest().permitAll()
        // .and().formLogin()
        // .and().httpBasic();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
