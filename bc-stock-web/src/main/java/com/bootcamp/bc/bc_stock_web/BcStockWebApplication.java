package com.bootcamp.bc.bc_stock_web;

import java.util.TimeZone;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BcStockWebApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Hong_Kong"));
		SpringApplication.run(BcStockWebApplication.class, args);
	}

}
