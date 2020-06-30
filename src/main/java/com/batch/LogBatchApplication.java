package com.batch;

import com.batch.core.anotacao.UseCase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.FilterType.ANNOTATION;

@SpringBootApplication
@ComponentScan(
        includeFilters = {
                @ComponentScan.Filter(type = ANNOTATION, value = UseCase.class),
        })
public class LogBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogBatchApplication.class, args);
    }

}
