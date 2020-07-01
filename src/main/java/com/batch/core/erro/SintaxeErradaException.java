package com.batch.core.erro;

import com.batch.core.erro.codigo.CodigoErroNegocio;

import java.util.Collections;

public class SintaxeErradaException extends AplicacaoException{

    public SintaxeErradaException() {
        super(CodigoErroNegocio.SINTAXE_ERRADA, Collections.emptyList(),
                CodigoErroNegocio.SINTAXE_ERRADA.getMensagemGenerica(), null);
    }
}
