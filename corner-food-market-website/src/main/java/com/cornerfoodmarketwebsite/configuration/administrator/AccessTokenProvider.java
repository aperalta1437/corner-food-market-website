package com.cornerfoodmarketwebsite.configuration.administrator;

import com.cornerfoodmarketwebsite.business.dto.request.domain.AdministratorUserDetails;
import com.cornerfoodmarketwebsite.business.service.AdministratorUserDetailsService;
import com.cornerfoodmarketwebsite.business.service.utils.TokenDetails;
import com.cornerfoodmarketwebsite.configuration.utils.ClientOriginProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.management.relation.Role;
import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class AccessTokenProvider {
//    private static final long serialVersionUID = 2569800841756370596L;

    @Value(value = "${administrator.jwt.access-token.valid-timeframe}")
    private int validTimeframe;
    private final AdministratorUserDetailsService administratorUserDetailsService;
    private final HashMap<Integer, ClientOriginProperties> clientOriginProperties;

    public TokenDetails createToken(String email, int originNumber) {
        Claims claims = Jwts.claims().setSubject(email);
//        claims.put("auth", role);

        final Date now = new Date();
        final long createdAt = now.getTime();
        return new TokenDetails(Jwts.builder().setClaims(claims).setIssuedAt(now)
                .setExpiration(new Date(createdAt + validTimeframe))
                .signWith(SignatureAlgorithm.HS256, clientOriginProperties.get(originNumber).getJwt().getAccessToken().getSecretKey()).compact(), validTimeframe, createdAt);
    }

    public Authentication getAuthentication(String email) {
        AdministratorUserDetails administratorUserDetails = (AdministratorUserDetails) this.administratorUserDetailsService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(administratorUserDetails, null, administratorUserDetails.getAuthorities());
    }

    public Claims getClaimsFromToken(String token, int originNumber) {
        return Jwts.parser().setSigningKey(clientOriginProperties.get(originNumber).getJwt().getAccessToken().getSecretKey()).parseClaimsJws(token).getBody();
    }
}
