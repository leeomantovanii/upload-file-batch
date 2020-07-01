package com.batch.core.erro;

import com.batch.core.erro.codigo.CodigoErroNegocio;

import java.util.Collections;

public class DadosDuplicadosException extends AplicacaoException {

    public DadosDuplicadosException() {
        super(CodigoErroNegocio.DADOS_DUPLICADOS, Collections.emptyList(),
                CodigoErroNegocio.DADOS_DUPLICADOS.getMensagemGenerica(), null);
    }
}
