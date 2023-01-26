package com.example.BankManagement.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.BankManagement.util.SecurityConstants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/*
 * This filter is implemented in order to generate
 * a JWT Token for the current user in the filter chain.
 * 
 * This code mostly build a JWT token and set up the parameters.
 * To see the parameters needed for a Token see the IETF standards
 */
public class JWTTokenGeneratorFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException 
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (null != authentication) {
			SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));//generate the secret key
			String jwt = Jwts.builder().setIssuer("Bank Management").setSubject("JWT Token")// build the Token, issuer = entity that emits the token
				.claim("username", authentication.getName())
				.claim("authorities", populateAuthorities(authentication.getAuthorities()))
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + 300000000))
				.signWith(key).compact();
			response.setHeader(SecurityConstants.JWT_HEADER, jwt);
		}

		filterChain.doFilter(request, response);
    }

    /*
     * With this method inherited from OncePerRequestFilter
     * we say to Spring that this filter should be used
     * only when the user try to connect, which is when the
     * path is equal to "/clients"
     */
    @Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		return !request.getServletPath().equals("/clients");
	}

    private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
		Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority authority : collection) {
        	authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
	}   
}