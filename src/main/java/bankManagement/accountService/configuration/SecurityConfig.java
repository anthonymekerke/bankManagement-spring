package bankManagement.accountService.configuration;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import bankManagement.accountService.service.JpaUserDetailsService;
import bankManagement.accountService.util.SecurityConstants;

@Configuration
@EnableWebSecurity //mark this class as the default web configurer
public class SecurityConfig {

    private final JpaUserDetailsService jpaUserDetailsService;
    private RsaKeyProperties rsaKeys;

    public SecurityConfig(
        RsaKeyProperties rsaKeys, 
        JpaUserDetailsService jpaUserDetailsService){
        this.rsaKeys = rsaKeys;
        this.jpaUserDetailsService = jpaUserDetailsService;
    }

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
     * development mode:
     *  - custom configuration for built-in usage of JWT
     *  - In deploy mode SessionManagement & CSRF must not be disable at same time -> open to csrf attack
     *  - Checkup for CORS configuration
     */
    @Profile("deploy")
    @Bean
    SecurityFilterChain testjwtSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable()) // disable CSRF
            .authorizeRequests( auth -> auth
                .anyRequest().authenticated() // user must be authenticated for any request
            )
            .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
            .userDetailsService(jpaUserDetailsService) // tells spring that this is the UserDetailsService we want to authentication
            .cors(Customizer.withDefaults()) // Spring by default lookup for a Bean called 'corsConfigurationSource', which we "override" with our own implementation
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // never create a HttpSession
            .httpBasic(Customizer.withDefaults()) // "Spring Security’s HTTP Basic Authentication support is enabled by default. However, as soon as any servlet-based configuration is provided, HTTP Basic must be explicitly provided."
            .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        //config.setAllowedOrigins(Collections.singletonList("http://localhost:4200")); // to use when need to be accessed from particular url
        config.setAllowedOriginPatterns(Collections.singletonList("*")); // to use when call from anywhere
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowCredentials(true);
        config.setAllowedHeaders(Collections.singletonList("*"));
        config.setExposedHeaders(Arrays.asList(SecurityConstants.AUTHORIZATION_HEADER));
        config.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(rsaKeys.getPublicKey()).build();
    }

    @Bean
    JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(rsaKeys.getPublicKey()).privateKey(rsaKeys.getPrivateKey()).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
