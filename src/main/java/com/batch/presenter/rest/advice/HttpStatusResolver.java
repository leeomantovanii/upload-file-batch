package com.batch.presenter.rest.advice;

import com.batch.core.erro.DadosDuplicadosException;
import com.batch.core.erro.NaoEncontradoException;
import com.batch.core.erro.SintaxeErradaException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class HttpStatusResolver {

    public HttpStatus resolverStatus(Throwable ex) {
        if (null == ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }

        if (ex instanceof NaoEncontradoException) {
            return HttpStatus.NOT_FOUND;
        }

        if (ex instanceof DadosDuplicadosException){
            return HttpStatus.UNPROCESSABLE_ENTITY;
        }

        if (ex instanceof SintaxeErradaException){
            return HttpStatus.BAD_REQUEST;
        }

        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
