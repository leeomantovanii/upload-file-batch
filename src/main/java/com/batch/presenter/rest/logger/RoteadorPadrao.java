package com.batch.presenter.rest.logger;

import com.batch.core.erro.AplicacaoException;
import com.batch.core.erro.codigo.CodigoErro;
import com.batch.core.erro.codigo.CodigoErroNegocio;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RoteadorPadrao implements RoteadorLog {

    private final Logger logger;
    private static final List<CodigoErro> codigosDebug =
            Arrays.asList(
                    CodigoErroNegocio.NAO_ENCONTRADO
            );

    @Override
    public void rotearLog(AplicacaoException erro){
        if (codigosDebug.contains(erro.getCodigoErro()) ||
        erro.getCause() instanceof AplicacaoException){
            logger.debug("Erro para debug lançado", erro);
        }else{
            logger.error("Exception genérica lançada, erro");
        }
    }
}
