package com.cornerfoodmarketwebsite.configuration.administrator;

import com.cornerfoodmarketwebsite.business.dto.request.domain.AdministratorUserDetails;
import com.cornerfoodmarketwebsite.business.service.AdministratorUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Base64;
import java.util.Date;

@Component
public class TfaJwtTokenProvider implements Serializable {
    private static final long serialVersionUID = 2569800841756370596L;

    @Value(value = "${jwt.admin-tfa-secret-key}")
    private String secretKey;

    @PostConstruct      // So it executes after dependency injection takes place.
    protected void init() {
        // Convert secret key to byte array
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    private long validityInMilliseconds = 2 * 60 * 1000;    // 2 minutes

    public String createToken(String email) {
        Claims claims = Jwts.claims().setSubject(email);
//        claims.put("auth", role);

        Date now = new Date();
        return Jwts.builder().setClaims(claims).setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + validityInMilliseconds))
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    @Autowired
    private AdministratorUserDetailsService administratorUserDetailsService;

    public Authentication getAuthentication(String email) {
        AdministratorUserDetails administratorUserDetails = (AdministratorUserDetails) this.administratorUserDetailsService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(administratorUserDetails.getUsername(), administratorUserDetails.getPassword(), administratorUserDetails.getAuthorities());
    }

    public Claims getClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }
}

