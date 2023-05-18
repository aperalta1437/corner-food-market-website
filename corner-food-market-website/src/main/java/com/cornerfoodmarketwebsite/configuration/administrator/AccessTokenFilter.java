package com.cornerfoodmarketwebsite.configuration.administrator;

import com.cornerfoodmarketwebsite.exception.InvalidAccessTokenRuntimeException;
import com.cornerfoodmarketwebsite.exception.UnauthorizedUserRuntimeException;
import com.cornerfoodmarketwebsite.exception.administrator.FailedAccountAuthenticationRuntimeException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static com.cornerfoodmarketwebsite.helper.Constants.ACCESS_TOKEN_HEADER_NAME;
import static com.cornerfoodmarketwebsite.helper.Constants.ORIGIN_NUMBER_HEADER_NAME;

// This is a spring security authentication filter. Only operates while authenticating.
@Slf4j
@RequiredArgsConstructor
public class AccessTokenFilter extends OncePerRequestFilter {

    private final AccessTokenProvider accessTokenProvider;

    @Override
    public void doFilterInternal(HttpServletRequest httpServletRequest, @NotNull HttpServletResponse httpServletResponse, @NotNull FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = httpServletRequest.getHeader(ACCESS_TOKEN_HEADER_NAME);

        if (!StringUtils.isEmpty(jwtToken)) {
            try {
                Claims claims = accessTokenProvider.getClaimsFromToken(jwtToken, Integer.parseInt(httpServletRequest.getHeader(ORIGIN_NUMBER_HEADER_NAME)));

                if (!claims.getExpiration().before(new Date())) {
                    Authentication authentication = accessTokenProvider.getAuthentication(claims.getSubject());
                    if (authentication.isAuthenticated()) {
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    } else {
                        throw new FailedAccountAuthenticationRuntimeException();
                    }
                } else {
                    SecurityContextHolder.clearContext();
                    throw new InvalidAccessTokenRuntimeException();
                }
            } catch (ExpiredJwtException expiredJwtException) {
                SecurityContextHolder.clearContext();
                throw new InvalidAccessTokenRuntimeException();
            }

        } else {
            throw new UnauthorizedUserRuntimeException();
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
