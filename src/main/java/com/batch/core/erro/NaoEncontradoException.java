package com.batch.core.erro;

import com.batch.core.erro.codigo.CodigoErroNegocio;

import java.util.Collections;

/**
 * Representa um erro de dado não encontrado
 *
 * @author Leonardo
 */
public class NaoEncontradoException extends AplicacaoException {

    public NaoEncontradoException() {
        super(CodigoErroNegocio.NAO_ENCONTRADO, Collections.emptyList(),
                CodigoErroNegocio.NAO_ENCONTRADO.getMensagemGenerica(), null);
    }
}
