package com.reportapp.demo.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtService {

    private static final String SECRET_KEY = "586E3272357538782F413F4428472B4B6250655368566B597033733676397924";

    public String getToken(UserDetails usuario) {
        return getToken(new HashMap<>(), usuario);
    }

    private String getToken(Map<String, Object> claims, UserDetails usuario) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 60 * 24 * 60 * 60 * 1000);
        return Jwts
        .builder()
        .setClaims(claims)
        .setSubject(usuario.getUsername())
        .setIssuedAt(now)
        .setExpiration(expiration)
        .signWith(getKey(), SignatureAlgorithm.HS256)
        .compact();
    }

    private Key getKey() {
        byte[] keyBytes=Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
     }

    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

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

            /**
     * Obtiene el token de autenticación a partir de la solicitud HttpServletRequest proporcionada.
     *
     * @param  request  el objeto HttpServletRequest que contiene la información de la solicitud
     * @return          el token de autenticación extraído del encabezado de la solicitud, o null si no se encuentra
     */
    public String getTokenFromRequest(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);

        }
        return null;
    }
}
