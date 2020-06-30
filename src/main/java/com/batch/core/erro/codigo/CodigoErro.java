package com.batch.core.erro.codigo;

import lombok.Getter;

@Getter
public class CodigoErro {

    private final int codigo;

    private final String mensagemGenerica;

    /**
     * Construtor usado para estabelecer padrões de códigos de erros
     *
     * @param major Codigo principal do erro
     * @param minor Codigo secundario erro menor
     * @param mensagemGenerica Mensagem generica do erro
     */
    public CodigoErro(int major, int minor, String mensagemGenerica) {
        this.codigo = (major * 100000) + minor;
        this.mensagemGenerica = mensagemGenerica;

    }
}
