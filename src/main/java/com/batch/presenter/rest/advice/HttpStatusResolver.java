package com.batch.presenter.rest.advice;

import com.batch.core.erro.NaoEncontradoException;
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

        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
