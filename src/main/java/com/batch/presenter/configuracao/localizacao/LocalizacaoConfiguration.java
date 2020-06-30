package com.batch.presenter.configuracao.localizacao;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Configura o spring para carregar arquivos de mensagens .properties em UT-8 e configura o local
 */

@Configuration
public class LocalizacaoConfiguration {

    @Bean
    public PropertiesFactoryBean propertiesFactoryBean(){
        PropertiesFactoryBean factory = new PropertiesFactoryBean();
        factory.setFileEncoding("UTF-8");
        return factory;
    }

    @Bean("mensagens")
    public MessageSource messagesResource(){
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

        messageSource.setBasename("localizacao/mensagens");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean(){
        LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
        factory.setValidationMessageSource(messagesResource());
        return factory;
    }
}
