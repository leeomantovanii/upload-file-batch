package com.batch.core.gateway;

public interface LocalizacaoGateway {

    /**
     * Resolve uma mensagem por uma chave
     *
     * @param chave A chave da mensagem
     * @param parametro Parametros opcionais a serem formatados
     * @return uma {@code String} da mensagem recuperada pela chave
     */
    String getMensagem (String chave, Object... parametro);
}
