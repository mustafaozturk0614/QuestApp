package com.project.questapp.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
	@Value("${quest.app.secret}")
	private  String APP_SECRET;
	@Value("${quest.app.expires.in}")
	private  long EXPIRES_IN;


	public String genaretJwtToken(Authentication authentication){
		JwtUserDetails userDetails=(JwtUserDetails) authentication.getPrincipal();
		Date expireDate= new Date(new Date().getTime()+EXPIRES_IN);
		return Jwts.builder().setSubject(Long.toString(userDetails.getId()))
				.setIssuedAt(new Date()).setExpiration(expireDate).signWith(SignatureAlgorithm.HS512,APP_SECRET).compact();

	}
  	Claims tokenParse(String token){

		return Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody();
	}
	long getUserIdFromJwt(String token){
		Claims claims=tokenParse(token);
		return  Long.parseLong(claims.getSubject());

	}


	boolean validateToken(String token){
		try {
			tokenParse(token);
			return  !isTokenExpired(token);
		}catch (SignatureException e ){
			return  false;

		}catch ( ExpiredJwtException e){
			return  false;

		}catch ( UnsupportedJwtException e){
			return  false;

		}
		catch ( IllegalArgumentException e){
			return  false;

		}
	}

	private boolean isTokenExpired(String token) {
		Date expiration=tokenParse(token).getExpiration();

		return  expiration.before(new Date());

	}

}
