package com.cornerfoodmarketwebsite.configuration.administrator;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

// This is a spring security authentication filter. Only operates while authenticating.
@Slf4j
@RequiredArgsConstructor
public class TfaJwtTokenFilter extends OncePerRequestFilter {

    private final TfaJwtTokenProvider tfaJwtTokenProvider;

    @Override
    public void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = httpServletRequest.getHeader("Authorization");

        if (jwtToken != null) {
            Claims claims = tfaJwtTokenProvider.getClaimsFromToken(jwtToken);
            if (!claims.getExpiration().before(new Date())) {
                Authentication authentication = tfaJwtTokenProvider.getAuthentication(claims.getSubject());
                if (authentication.isAuthenticated()) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } else {
                SecurityContextHolder.clearContext();
                try {
                    httpServletResponse.getWriter().println(new JSONObject().put("message", "Expired or invalid JWT token"));
                    httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        } else {
            try {
                httpServletResponse.getWriter().println(new JSONObject().put("message", "User is not authorized"));
                httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
