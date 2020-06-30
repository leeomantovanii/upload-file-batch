package com.batch.presenter.rest.advice;


import com.batch.core.erro.AplicacaoException;
import com.batch.core.erro.ProcessarExceptionUseCase;
import com.batch.presenter.rest.logger.RoteadorLog;
import com.batch.presenter.rest.response.ErroAplicacaoResponse;
import com.batch.presenter.rest.response.ErroResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Redireciona um {@link Throwable} para {@link com.batch.core.erro.ProcessarExceptionUseCase}
 */
@ControllerAdvice
@RequiredArgsConstructor
public class ErroAplicacaoAdvice {

    private final ProcessarExceptionUseCase processarExceptionUseCase;
    private final ErroResponseMapper erroMapper;
    private final HttpStatusResolver httpsStatusResolver;
    private final RoteadorLog roteadorLog;

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErroAplicacaoResponse> receberException(Throwable ex){
        AplicacaoException erroApp = processarExceptionUseCase.processarException(ex);

        roteadorLog.rotearLog(erroApp);

        return ResponseEntity
                .status(httpsStatusResolver.resolverStatus(ex))
                .body(erroMapper.erroAplicacao(erroApp));
    }
}
