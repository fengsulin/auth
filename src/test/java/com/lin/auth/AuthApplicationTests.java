package com.lin.auth;

import com.lin.auth.utils.RSAUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.PrivateKey;

@SpringBootTest
class AuthApplicationTests {

	@Test
	void contextLoads() {
		PrivateKey s1 = RSAUtil.getPrivateKey("JXU2NjI1JXU2QzVGJXU2QzM0JXU2Njk2JXU5RTJEJXU1MTQ4JXU3N0U1");
		String format = s1.getFormat();
		System.out.println(format);
	}

}
