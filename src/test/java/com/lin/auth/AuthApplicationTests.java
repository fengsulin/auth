package com.lin.auth;

import com.lin.auth.utils.JWTUtils;
import com.lin.auth.utils.RSAUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.PrivateKey;
import java.util.HashMap;

@SpringBootTest
class AuthApplicationTests {

	@Test
	void contextLoads() {
		PrivateKey s1 = RSAUtils.getPrivateKey("JXU2NjI1JXU2QzVGJXU2QzM0JXU2Njk2JXU5RTJEJXU1MTQ4JXU3N0U1");
		String format = s1.getFormat();
		System.out.println(format);
	}

	@Test
	void jwtTest(){
		HashMap<String, Object> map = new HashMap<>();
		map.put("id","1234");
		map.put("username","lin e");
		String jwtToken = JWTUtils.getJwtToken(map);
		System.out.println(jwtToken);

	}
	@Test
	void jwtTest2(){
		Jws<Claims> claimsJws = JWTUtils.decode("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsaW4tand0IiwiaWF0IjoxNjU4OTA2NTYwLCJleHAiOjE2NTg5OTI5NjAsImlkIjoiMTIzNCIsInVzZXJuYW1lIjoibGluIGUifQ.a6EINtc-2-S_KhcdXo7Tyzu51NxKJ4Rb6qDeAhPD0Tc");
		String signature = claimsJws.getSignature();
		Claims body = claimsJws.getBody();
		String username = (String) body.get("username");
		String id = body.getId();
		System.out.println(username);
		System.out.println(id);
	}

	@Test
	void jwtRsaTest(){
		HashMap<String, Object> map = new HashMap<>();
		map.put("id","1234");
		map.put("username","lin e");
		String jwtTokenRsa = JWTUtils.getJwtTokenRsa(map);
		System.out.println(jwtTokenRsa);

		Jws<Claims> claimsJws = JWTUtils.decodeRsa(jwtTokenRsa);
		String signature = claimsJws.getSignature();
		Claims body = claimsJws.getBody();
		String subject = body.getSubject();
		System.out.println(signature);
		System.out.println(subject);
	}

}
