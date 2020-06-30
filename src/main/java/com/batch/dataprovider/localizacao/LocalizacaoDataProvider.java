package com.batch.dataprovider.localizacao;

import com.batch.core.gateway.LocalizacaoGateway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * Essa classe retorna uma mensagem baseada em uma key pelo Spring. Essas mensagens estão em arquivos
 * {@code .properties} no classpath
 */
@Component
public class LocalizacaoDataProvider implements LocalizacaoGateway {

    private MessageSource messageSource;

    public LocalizacaoDataProvider(@Qualifier("mensagens") MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String getMensagem(String chave, Object... parametros) {
        try {
            return messageSource.getMessage(chave, parametros, LocaleContextHolder.getLocale());
        } catch (Exception ignored) {
            return chave;
        }
    }
}
