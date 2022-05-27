//package com.cornerfoodmarketwebsite.configuration;
//
//import org.codehaus.jettison.json.JSONException;
//import org.codehaus.jettison.json.JSONObject;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.HashMap;
//
////@Component
////@Order(2)
//public class RequestDomainNameFilter extends OncePerRequestFilter {
////    @Value("#{${pss.clients-domain-names-to-has-access-map}}")
//    private HashMap<String, Boolean> pssClientDomainToHasAccessMap;
//    @Override
//    protected void doFilterInternal(HttpServletRequest httpServletRequest, @NotNull HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//        if (!"OPTIONS".equalsIgnoreCase(httpServletRequest.getMethod())) {
//            String clientDomainName = httpServletRequest.getHeader("Domain-Name");
//            if (clientDomainName == null || !pssClientDomainToHasAccessMap.containsKey(clientDomainName) || !pssClientDomainToHasAccessMap.get(clientDomainName)) {
//                try {
//                    httpServletResponse.getWriter().println(new JSONObject().put("exception", "Unauthorized client domain."));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                return;
//            }
//        }
//        filterChain.doFilter(httpServletRequest, httpServletResponse);
//    }
//}
