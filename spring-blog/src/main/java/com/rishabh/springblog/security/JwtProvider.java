package com.rishabh.springblog.security;

import java.io.IOException;
import java.io.InputStream;
//import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.annotation.PostConstruct;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.rishabh.springblog.exception.SpringBlogException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {

//	private Key key;

	private KeyStore keyStore;

	@PostConstruct
	public void init() {
//		key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
		try {
			keyStore = KeyStore.getInstance("JKS");
			InputStream resourceAsStream = getClass().getResourceAsStream("/springblog.jks");
			keyStore.load(resourceAsStream, "Rishabh123@".toCharArray());
		} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
			throw new SpringBlogException("Exception occured while loading keystore");
		}
	}

	public String generateToken(Authentication authentication) {
		User principal = (User) authentication.getPrincipal();
//		return Jwts.builder().setSubject(principal.getUsername()).signWith(key).compact();
		return Jwts.builder().setSubject(principal.getUsername()).signWith(getPrivateKey()).compact();
	}

	private PrivateKey getPrivateKey() {
		try {
			return (PrivateKey) keyStore.getKey("springblog", "Rishabh123@".toCharArray());
		} catch (UnrecoverableKeyException | KeyStoreException | NoSuchAlgorithmException e) {
			throw new SpringBlogException("Exception occured while retrieving private key from keystore");
		}
	}

	private PublicKey getPublicKey() {
		try {
			return keyStore.getCertificate("springblog").getPublicKey();
		} catch (KeyStoreException e) {
			throw new SpringBlogException("Exceptionn occured while retrieving public key from keystore");
		}
	}

	@SuppressWarnings("deprecation")
	public boolean validateToken(String jwt) {
//		Jwts.parser().setSigningKey(key).parseClaimsJws(jwt);
		Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(jwt);
		return true;
	}

	@SuppressWarnings("deprecation")
	public String getUsernameFromJwt(String token) {
//		Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
		Claims claims = Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(token).getBody();

		return claims.getSubject();
	}
}
