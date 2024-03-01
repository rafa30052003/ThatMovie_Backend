package org.thatmovie.Jwt;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.thatmovie.model.Member;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import static io.jsonwebtoken.Jwts.*;


@Service
public class JwtService {

    private static final String SECRET_KEY="586E3272357538782F413F4428472B4B6250655368566B597033733676397924";

    /**
     * Genera un token JWT para el usuario proporcionado.
     *
     * @param member  UserDetails del usuario para el que se generará el token.
     * @return El token JWT generado.
     */
    public String getToken(Member member) {
        return getToken(new HashMap<>(), member);
    }

    private String getToken(Map<String,Object> extraClaims, Member member) {
        long expirationMillis = 10000 * 60 * 60 * 24;
        return builder()
                .setClaims(extraClaims)
                .setSubject(member.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expirationMillis))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey() {
        byte[] keyBytes=Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Obtiene el nombre de usuario (subject) del token JWT.
     *
     * @param token El token JWT.
     * @return El nombre de usuario extraído del token.
     */
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    /**
     * Verifica si el token JWT es válido para el UserDetails proporcionado.
     *
     * @param token       El token JWT.
     * @param userDetails El UserDetails para verificar el token.
     * @return true si el token es válido para el UserDetails dado, de lo contrario false.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username=getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }

    private Claims getAllClaims(String token)
    {
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T getClaim(String token, Function<Claims,T> claimsResolver)
    {
        final Claims claims=getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpiration(String token)
    {
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token)
    {
        return getExpiration(token).before(new Date());
    }
}