package com.cornerfoodmarketwebsite.configuration.administrator;

import com.cornerfoodmarketwebsite.exception.InvalidJwtTokenRuntimeException;
import com.cornerfoodmarketwebsite.exception.UnauthorizedUserRuntimeException;
import com.cornerfoodmarketwebsite.exception.administrator.FailedAccountAuthenticationRuntimeException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.jetbrains.annotations.NotNull;
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

import static com.cornerfoodmarketwebsite.helper.Constants.ORIGIN_NUMBER_HEADER_NAME;
import static com.cornerfoodmarketwebsite.helper.Constants.TFA_ACCESS_TOKEN_HEADER_NAME;

// This is a spring security authentication filter. Only operates while authenticating.
@Slf4j
@RequiredArgsConstructor
public class TfaJwtTokenFilter extends OncePerRequestFilter {

    private final TfaJwtTokenProvider tfaJwtTokenProvider;

    @Override
    public void doFilterInternal(HttpServletRequest httpServletRequest, @NotNull HttpServletResponse httpServletResponse, @NotNull FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = httpServletRequest.getHeader(TFA_ACCESS_TOKEN_HEADER_NAME);

        if (!StringUtils.isEmpty(jwtToken)) {
            try {
                Claims claims = tfaJwtTokenProvider.getClaimsFromToken(jwtToken, Integer.parseInt(httpServletRequest.getHeader(ORIGIN_NUMBER_HEADER_NAME)));

                if (!claims.getExpiration().before(new Date())) {
                    Authentication authentication = tfaJwtTokenProvider.getAuthentication(claims.getSubject());
                    if (authentication.isAuthenticated()) {
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    } else {
                        throw new FailedAccountAuthenticationRuntimeException();
                    }
                } else {
                    SecurityContextHolder.clearContext();
                    throw new InvalidJwtTokenRuntimeException();
                }
            } catch (ExpiredJwtException expiredJwtException) {
                SecurityContextHolder.clearContext();
                throw new InvalidJwtTokenRuntimeException();
            }
        } else {
            throw new UnauthorizedUserRuntimeException();
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
