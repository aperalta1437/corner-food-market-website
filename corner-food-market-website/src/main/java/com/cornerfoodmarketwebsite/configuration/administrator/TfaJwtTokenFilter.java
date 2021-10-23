package com.cornerfoodmarketwebsite.configuration.administrator;

import io.jsonwebtoken.Claims;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class TfaJwtTokenFilter extends OncePerRequestFilter {
    private static Logger log = LoggerFactory.getLogger(JwtTokenFilter.class);

    private TfaJwtTokenProvider tfaJwtTokenProvider;

    public TfaJwtTokenFilter(TfaJwtTokenProvider tfaJwtTokenProvider) {
        this.tfaJwtTokenProvider = tfaJwtTokenProvider;
    }

    @Override
    public void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String tfaJwtToken = httpServletRequest.getHeader("Authorization");
        if (tfaJwtToken != null) {
            try {
                Claims claims = tfaJwtTokenProvider.getClaimsFromToken(tfaJwtToken);
                if (!claims.getExpiration().before(new Date())) {
                    Authentication authentication = tfaJwtTokenProvider.getAuthentication(claims.getSubject());
                    if (authentication.isAuthenticated()) {
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            } catch (RuntimeException e) {
                try {
                    SecurityContextHolder.clearContext();
                    httpServletResponse.setContentType("application/json");
                    httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    httpServletResponse.getWriter().println(new JSONObject().put("exception", "expired or invalid JWT token " + e.getMessage()));
                } catch (IOException | JSONException jsonException) {
                    jsonException.printStackTrace();
                }
                return;
            }
        } else {
            log.info("Access Token is being created - authenticate method");
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
