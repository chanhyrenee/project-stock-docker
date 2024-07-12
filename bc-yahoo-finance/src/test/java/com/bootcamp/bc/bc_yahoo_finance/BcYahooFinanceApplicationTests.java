package com.bootcamp.bc.bc_yahoo_finance;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "env", matches = "production")
class BcYahooFinanceApplicationTests {

	@Test
	void contextLoads() {
	}

}
