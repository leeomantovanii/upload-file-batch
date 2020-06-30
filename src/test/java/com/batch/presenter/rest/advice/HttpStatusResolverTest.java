package com.batch.presenter.rest.advice;

import com.batch.core.erro.NaoEncontradoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.exceptions.base.MockitoException;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HttpStatusResolverTest {

    public static final HttpStatusResolver resolver = new HttpStatusResolver();

    @Test
    @DisplayName("Deve transformar recurso não encontrato em erro 404")
    public void erro404() {
        NaoEncontradoException exception = new NaoEncontradoException();

        assertThat(resolver.resolverStatus(exception)).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DisplayName("Deve transformar erros desconhecidos em erro 500")
    public void erro500() {
        MockException exception = new MockException();

        assertThat(resolver.resolverStatus(exception)).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private static class MockException extends RuntimeException {

    }


}