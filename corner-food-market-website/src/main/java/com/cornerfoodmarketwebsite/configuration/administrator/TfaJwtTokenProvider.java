package com.cornerfoodmarketwebsite.configuration.administrator;

import com.cornerfoodmarketwebsite.business.dto.request.domain.AdministratorUserDetails;
import com.cornerfoodmarketwebsite.business.service.AdministratorUserDetailsService;
import com.cornerfoodmarketwebsite.configuration.utils.ClientOriginProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class TfaJwtTokenProvider {
//    private static final long serialVersionUID = 2569800841756370596L;

    @Value(value = "#{${administrator.jwt.tfa-access-token.valid-timeframe}}")      // "#" is needed to add the expression that comes from Yaml file since we can't perform arithmetic in YAML file.
    private int validTimeframe;
    private final AdministratorUserDetailsService administratorUserDetailsService;
    private final HashMap<Integer, ClientOriginProperties> clientOriginProperties;

    public String createToken(String email, int originNumber) {
        Claims claims = Jwts.claims().setSubject(email);
//        claims.put("auth", role);

        Date now = new Date();
        return Jwts.builder().setClaims(claims).setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + validTimeframe))
                .signWith(SignatureAlgorithm.HS256, clientOriginProperties.get(originNumber).getJwt().getTfaAccessToken().getSecretKey()).compact();
    }

    public Authentication getAuthentication(String email) {
        AdministratorUserDetails administratorUserDetails = (AdministratorUserDetails) this.administratorUserDetailsService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(administratorUserDetails, null, administratorUserDetails.getAuthorities());
    }

    public Claims getClaimsFromToken(String token, int originNumber) {
        return Jwts.parser().setSigningKey(clientOriginProperties.get(originNumber).getJwt().getTfaAccessToken().getSecretKey()).parseClaimsJws(token).getBody();
    }
}

