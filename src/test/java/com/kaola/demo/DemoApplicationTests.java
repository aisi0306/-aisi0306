package com.kaola.demo;

import com.kaola.demo.model.ResultMap;
import com.kaola.demo.service.IndexService;
import com.kaola.demo.service.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private LoginService loginService;
	@Test
	public void contextLoads() {
		ResultMap login = loginService.login("123", "123");
	}

}

