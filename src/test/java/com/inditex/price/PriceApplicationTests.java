package com.inditex.price;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class PriceApplicationTests {

	
	@Autowired
    private Environment env;
	
	@Test
    void contextLoads() {
        String data = env.getProperty("spring.r2dbc.url");
        assertThat(data).isEqualTo("r2dbc:h2:mem:///test2db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
    }
}