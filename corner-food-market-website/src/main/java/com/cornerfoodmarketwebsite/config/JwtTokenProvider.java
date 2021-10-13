package com.cornerfoodmarketwebsite.config;

import com.cornerfoodmarketwebsite.business.dto.request.domain.AdministratorUserDetails;
import com.cornerfoodmarketwebsite.business.service.AdministratorUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider implements Serializable {
    private static final long serialVersionUID = 2569800841756370596L;

    private String secretKey = "secret";

    @PostConstruct      // So it executes after dependency injection takes place.
    void init() {
        // Convert secret key to byte array
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // TODO change to proper JWT reset time.
    private long validityInMilliseconds = 10 * 60 * 60;

    public String createToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);

        Date now = new Date();
        return Jwts.builder().setClaims(claims).setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + validityInMilliseconds))
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    @Autowired
    private AdministratorUserDetailsService administratorUserDetailsService;

    public Authentication getAuthentication(String token) {
        String username = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        AdministratorUserDetails administratorUserDetails = (AdministratorUserDetails) this.administratorUserDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(administratorUserDetails, "", administratorUserDetails.getAuthorities());
    }

    public boolean validateToken(String token) {
        Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return true;
    }
}
