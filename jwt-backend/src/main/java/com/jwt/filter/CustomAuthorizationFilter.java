package com.jwt.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwt.security.SecurityConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static com.jwt.filter.FilterConstrains.BEARER;
import static com.jwt.filter.FilterConstrains.ROLES;

public class CustomAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            if (!request.getServletPath().equals("/login")) {

                String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
                if (Objects.nonNull(authorizationHeader) && authorizationHeader.startsWith(BEARER)) {

                    String token = authorizationHeader.substring(BEARER.length());
                    DecodedJWT decodedJWT = JWT.require(SecurityConfiguration.ALGORITHM).build().verify(token);

                    String username = decodedJWT.getSubject();
                    String[] roles = decodedJWT.getClaim(ROLES).asArray(String.class);

                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    Arrays.stream(roles).forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));

                    UsernamePasswordAuthenticationToken authenticationToken
                            = new UsernamePasswordAuthenticationToken(username, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            Map<String, String> errMess = new HashMap<>();
            errMess.put("error-message", e.getMessage());
            new ObjectMapper().writeValue(response.getOutputStream(), errMess);
        }
    }

}
