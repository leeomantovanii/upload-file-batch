package com.batch.core.erro;

import com.batch.core.erro.codigo.CodigoErro;
import com.batch.core.erro.codigo.CodigoErroInfraestrutura;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Classe responsável por realizar testes unitários na {@link AplicacaoException}
 */
class AplicacaoExceptionTest {

    public static final CodigoErro CODIGO_ERRO = CodigoErroInfraestrutura.DESCONHECIDO;

    @Test
    @DisplayName("Builder deve usar mensagem genérica por padrão")
    public void builderMensagemGenerica() {
        AplicacaoException ex = AplicacaoException.builder()
                .codigoErro(CODIGO_ERRO)
                .errosValidacao(Collections.singletonList(null))
                .cause(new IOException())
                .build();

        assertThat(ex.getMessage()).isEqualTo(CODIGO_ERRO.getMensagemGenerica());
    }

    @Test
    @DisplayName("Builder deve usar mensagem especificada quando necessário")
    public void builderMensagemCustomizada() {
        AplicacaoException ex = AplicacaoException.builder()
                .message("Mensagem")
                .codigoErro(CODIGO_ERRO)
                .build();

        assertThat(ex.getMessage()).isEqualTo("Mensagem");

    }

}