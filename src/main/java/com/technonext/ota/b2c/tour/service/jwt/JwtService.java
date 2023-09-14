//
//package com.technonext.ota.b2c.tour.service.jwt;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//import java.security.Key;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//
//
//@Service
//public class JwtService {
//
//
//    private static String signingKey;
//
//    private static long jwtExpiration;
//
//    private static final String PREFIX = "Bearer";
//
//    @Value("${application.security.jwt.signing-key}")
//    public void setSigningKeyStatic(String signingKey) {
//        JwtService.signingKey = signingKey;
//    }
//
//    @Value("${application.security.jwt.expiration}")
//    public void setJwtExpirationStatic(long jwtExpiration) {
//        JwtService.jwtExpiration = jwtExpiration;
//    }
//
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    public boolean isTokenValid(String token, UserDetails userDetails) {
//        return extractUsername(token).equals(userDetails.getUsername()) &&
//            !isTokenExpired(token);
//    }
//
//    private boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    public Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    public String generateAccessToken(UserDetails userDetails) {
//        return generateAccessToken(new HashMap<>(), userDetails, jwtExpiration);
//    }
//
//    public String generateAccessToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration) {
//        return Jwts
//            .builder()
//            .setClaims(extraClaims)
//            .setSubject(userDetails.getUsername())
//            .setIssuedAt(new Date())
//            .setExpiration(new Date(System.currentTimeMillis() + (expiration * 60 * 1000)))
//            .signWith(createSignKey(), SignatureAlgorithm.HS256)
//            .compact();
//    }
//
//    private Claims extractAllClaims(String token) {
//        return Jwts
//            .parserBuilder()
//            .setSigningKey(createSignKey())
//            .build()
//            .parseClaimsJws(token)
//            .getBody();
//    }
//
//    private static Key createSignKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(signingKey);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//
//}
