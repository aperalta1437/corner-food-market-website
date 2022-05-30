package com.cornerfoodmarketwebsite.configuration;

import com.cornerfoodmarketwebsite.configuration.utils.OriginProperties;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Order(2)
@RequiredArgsConstructor
public class CustomCorsFilter extends OncePerRequestFilter {
    @NonNull
    private final HashMap<Integer, OriginProperties> originPropertiesMap;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, @NotNull HttpServletResponse httpServletResponse, @NotNull FilterChain filterChain) throws ServletException, IOException {
        String originNumber = httpServletRequest.getHeader("Origin-Number");

        if (httpServletRequest.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.name())) {
            httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
            httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");
            httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
            httpServletResponse.setHeader("Access-Control-Allow-Headers", "*");
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        } else if (originNumber != null && originPropertiesMap.get(Integer.parseInt(originNumber)).isAllowed()) {
            httpServletResponse.setHeader("Access-Control-Allow-Origin", originPropertiesMap.get(Integer.parseInt(originNumber)).getOrigin());
            httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");
            httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
            httpServletResponse.setHeader("Access-Control-Allow-Headers", "*");
        } else {
            return;
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
