package com.example.BankManagement.config;

import java.util.Arrays;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.example.BankManagement.filter.JWTTokenGeneratorFilter;
import com.example.BankManagement.filter.JWTTokenValidatorFilter;
import com.example.BankManagement.util.SecurityConstants;

@Configuration
public class ProjectSecurityConfig {

    /*
     * Development mode:
     * Default Security configuration
     */
    @Profile("defaultsecure")
    @Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().anyRequest().authenticated().and().csrf().disable().httpBasic().and().formLogin();

        return http.build();
    }

    /*
     * Development mode:
     * All Security Disabled
     */
    @Profile("unsecured")
    @Bean
	SecurityFilterChain disabledSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().anyRequest().permitAll().and().csrf().disable().httpBasic().and().formLogin();

        return http.build();
    }

    /*
     * Deploy mode:
     * Custom configuration with JWT authentication, CORS & CSRF
     */
    @Profile("deploy")
    @Bean
	SecurityFilterChain deploySecurityFilterChain(HttpSecurity http) throws Exception {

        http
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .cors().configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.setAllowCredentials(true);
                config.setAllowedHeaders(Collections.singletonList("*"));
                config.setExposedHeaders(Arrays.asList(SecurityConstants.JWT_HEADER));
                config.setMaxAge(3600L);
                return config;
            }
        }).and()
        //.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
        .csrf().disable()
        .addFilterAfter(new JWTTokenGeneratorFilter(),BasicAuthenticationFilter.class)
        .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
        .authorizeRequests().anyRequest().authenticated()
        .and().formLogin()
        .and().httpBasic();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
