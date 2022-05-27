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
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
//                         FilterChain filterChain) throws IOException, ServletException {
//        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
////        HttpServletRequest request = (HttpServletRequest) servletRequest;
//
//        if ("OPTIONS".equalsIgnoreCase(servletRequest.getMethod())) {
//            httpServletResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
//            httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");
//            httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
//            httpServletResponse.setHeader("Access-Control-Allow-Headers", "*");
//            response.setStatus(HttpServletResponse.SC_OK);
//        } else {
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
//
//        filterChain.doFilter(servletRequest, servletResponse);
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, @NotNull HttpServletResponse httpServletResponse, @NotNull FilterChain filterChain) throws ServletException, IOException {
        String originNumber = httpServletRequest.getHeader("Origin-Number");

        httpServletResponse.setHeader("Access-Control-Allow-Origin", httpServletRequest.getMethod().equals(HttpMethod.OPTIONS.name()) ? "*" : (originNumber != null && originPropertiesMap.get(Integer.parseInt(originNumber)).isAllowed() ? originPropertiesMap.get(Integer.parseInt(originNumber)).getOrigin() : ""));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");
        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "*");

        if ("OPTIONS".equalsIgnoreCase(httpServletRequest.getMethod())) {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
