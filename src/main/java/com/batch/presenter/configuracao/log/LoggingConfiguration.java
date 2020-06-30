package com.batch.presenter.configuracao.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.MethodParameter;

import java.lang.reflect.Field;
import java.util.Optional;

/**
 * Utilizado para fazer a injeção de um logger do slf4j automaticamente
 */
@Configuration
public class LoggingConfiguration {

    @Bean
    @Scope("prototype")
    public Logger logger(InjectionPoint ip) {
        return LoggerFactory.getLogger(getClasseInjecao(ip));
    }

    private Class<?> getClasseInjecao(InjectionPoint ip) {
        return getClasseLocalInjecaoPorMetodo(ip)
                .orElseGet(() ->
                        getClasseLocalInjecaoPorField(ip)
                                .orElseThrow(IllegalArgumentException::new)

                );
    }

    private Optional<Class<?>> getClasseLocalInjecaoPorField(InjectionPoint ip) {
        return Optional.ofNullable(ip.getField())
                .map(Field::getDeclaringClass);
    }

    private Optional<Class<?>> getClasseLocalInjecaoPorMetodo(InjectionPoint ip) {
        return Optional.ofNullable(ip.getMethodParameter())
                .map(MethodParameter::getContainingClass);
    }
}
