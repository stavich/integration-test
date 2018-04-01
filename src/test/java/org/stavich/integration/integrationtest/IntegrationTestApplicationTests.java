package org.stavich.integration.integrationtest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTestApplicationTests {

	@Test
	public void contextLoads() throws InterruptedException {
		Thread.sleep(10000);
	}

}
