package io.hsjang.livepage.config.security;

import java.util.Map;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class HSAuthenticationProvider {

    public static String KEY = "live!@";
	
	public static Map<String,Object> parse(String token){
		return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
	}
	
	public static String compact(Map<String,Object> user){
		return Jwts.builder()
				.setClaims(user)
				.signWith(SignatureAlgorithm.HS256, KEY)
				.compact();
	}
}