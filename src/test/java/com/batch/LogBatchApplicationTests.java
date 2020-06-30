package com.batch;

import com.batch.core.anotacao.UseCase;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import static org.springframework.context.annotation.FilterType.ANNOTATION;

@SpringBootTest
@ComponentScan(
		includeFilters = {
				@ComponentScan.Filter(type = ANNOTATION, value = UseCase.class),
		})
class LogBatchApplicationTests {

	@Test
	void contextLoads() {
	}

}
