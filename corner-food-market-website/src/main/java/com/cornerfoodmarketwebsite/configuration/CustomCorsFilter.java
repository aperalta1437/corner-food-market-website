package com.cornerfoodmarketwebsite.configuration;

import com.cornerfoodmarketwebsite.configuration.utils.OriginProperties;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Order(Ordered.HIGHEST_PRECEDENCE + 2)
@RequiredArgsConstructor
public class CustomCorsFilter extends OncePerRequestFilter {
    @NonNull
    private final HashMap<Integer, OriginProperties> originPropertiesMap;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, @NotNull HttpServletResponse httpServletResponse, @NotNull FilterChain filterChain) throws ServletException, IOException {
        if (httpServletRequest.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.name())) {
            httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
            httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");
            httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
            httpServletResponse.setHeader("Access-Control-Allow-Headers", "*");
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            return;
        } else {
            String originNumber = httpServletRequest.getHeader("Origin-Number");
            if (originNumber != null && originPropertiesMap.get(Integer.parseInt(originNumber)).isAllowed()) {
                httpServletResponse.setHeader("Access-Control-Allow-Origin", originPropertiesMap.get(Integer.parseInt(originNumber)).getOrigin());
                httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");
                httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
                httpServletResponse.setHeader("Access-Control-Allow-Headers", "*");
            } else {
                try {
                    httpServletResponse.getWriter().println(new JSONObject().put("message", "Origin is not authorized"));
                    httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
