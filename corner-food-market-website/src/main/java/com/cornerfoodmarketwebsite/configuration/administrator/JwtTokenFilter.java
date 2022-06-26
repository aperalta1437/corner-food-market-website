package com.cornerfoodmarketwebsite.configuration.administrator;

import com.cornerfoodmarketwebsite.exception.ExpiredJwtTokenRuntimeException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Slf4j
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = httpServletRequest.getHeader("Authorization");

        if (jwtToken != null) {
            try {
                Claims claims = jwtTokenProvider.getClaimsFromToken(jwtToken);

                if (!claims.getExpiration().before(new Date())) {
                    Authentication authentication = jwtTokenProvider.getAuthentication(claims.getSubject());
                    if (authentication.isAuthenticated()) {
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                } else {
                    setExpiredJwtResponse(httpServletResponse);
                    return;
                }
            } catch (ExpiredJwtException expiredJwtException) {
                throw new ExpiredJwtTokenRuntimeException();
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

    private void setExpiredJwtResponse(HttpServletResponse httpServletResponse) {
        SecurityContextHolder.clearContext();
        try {
            httpServletResponse.getWriter().println(new JSONObject().put("message", "Expired or invalid JWT token"));
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
