package com.cornerfoodmarketwebsite.configuration.administrator;

import org.codehaus.jettison.json.JSONException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.codehaus.jettison.json.JSONObject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {
    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = httpServletRequest.getHeader("Authorization");
        try {
            if (jwtToken != null && jwtTokenProvider.validateToken(jwtToken)) {
                SecurityContextHolder.getContext().setAuthentication(jwtTokenProvider.getAuthentication(jwtToken));
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

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
