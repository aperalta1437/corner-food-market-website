package com.cornerfoodmarketwebsite.configuration.administrator;

import com.cornerfoodmarketwebsite.exception.InvalidJwtTokenRuntimeException;
import com.cornerfoodmarketwebsite.exception.NonExpiredJwtAccessTokenRuntimeException;
import com.cornerfoodmarketwebsite.exception.UnauthorizedUserRuntimeException;
import com.cornerfoodmarketwebsite.exception.administrator.FailedAccountAuthenticationRuntimeException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static com.cornerfoodmarketwebsite.helper.Constants.*;

// This is a spring security authentication filter. Only operates while authenticating.
@Slf4j
@RequiredArgsConstructor
public class RefreshTokenFilter extends OncePerRequestFilter {

    @Value(value = "${administrator.jwt.access-token.valid-timeframe}")
    private int accessTokenValidTimeframe;
    @Value(value = "${administrator.jwt.refresh-token.valid-timeframe}")
    private long refreshTokenValidTimeframe;
    private final RefreshTokenProvider refreshTokenProvider;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest httpServletRequest, @NotNull HttpServletResponse httpServletResponse, @NotNull FilterChain filterChain) throws ServletException, IOException {
        String refreshToken = httpServletRequest.getHeader(REFRESH_TOKEN_HEADER_NAME);
        String accessToken = httpServletRequest.getHeader(ACCESS_TOKEN_HEADER_NAME);

        if (!StringUtils.isEmpty(refreshToken) && !StringUtils.isEmpty(accessToken)) {
            try {
                int originNumber = Integer.parseInt(httpServletRequest.getHeader(ORIGIN_NUMBER_HEADER_NAME));
                Claims refreshTokenClaims = refreshTokenProvider.getClaimsFromToken(refreshToken, originNumber);
                Claims accessTokenClaims = jwtTokenProvider.getClaimsFromToken(accessToken, originNumber);

                if (!refreshTokenClaims.getExpiration().before(new Date()) && refreshTokenClaims.getSubject().equals(accessTokenClaims.getSubject()) && !refreshTokenClaims.getExpiration().after(new Date(new Date().getTime() + refreshTokenValidTimeframe - accessTokenValidTimeframe))) {

                    if (accessTokenClaims.getExpiration().before(new Date())) {
                        Authentication authentication = refreshTokenProvider.getAuthentication(refreshTokenClaims.getSubject());
                        if (authentication.isAuthenticated()) {
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        } else {
                            throw new FailedAccountAuthenticationRuntimeException();
                        }
                    } else {
                        SecurityContextHolder.clearContext();
                        throw new NonExpiredJwtAccessTokenRuntimeException();
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
