package com.batch.core.erro;

import com.batch.core.gateway.LocalizacaoGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ProcessarExceptionUseCaseTest {

    @Mock
    ResolverExceptionUseCase resolverExceptionUseCase;

    @Mock
    LocalizacaoGateway localizacaoGateway;

    @InjectMocks
    ProcessarExceptionUseCase useCase;

    @Test
    @DisplayName("Deve fazer o processamento da exception")
    void processarException() {
        RuntimeException causa = new RuntimeException();
        AplicacaoException exceptionConvertida = AplicacaoException.builder().build();

        when(resolverExceptionUseCase.resolverException(causa)).thenReturn(exceptionConvertida);

        AplicacaoException exceptionResolvida = useCase.processarException(causa);

        assertThat(exceptionResolvida.getCause()).isEqualTo(causa);
    }

    @Test
    @DisplayName("Deve resolver mensagem pela localizacao")
    void resolveMensagem() {
        String keyMensagem = "teste.teste2";
        String mensagem = "teste";

        AplicacaoException exceptionConvertida = AplicacaoException.builder()
                .message(keyMensagem)
                .errosValidacao(Arrays.asList(ErroValidacao.builder()
                                .nomeParametro("teste2")
                                .mensagem(keyMensagem)
                                .build(),
                        ErroValidacao.builder()
                                .nomeParametro("teste")
                                .mensagem(keyMensagem)
                                .build()))
                .build();

        RuntimeException causa = new RuntimeException();

        when(resolverExceptionUseCase.resolverException(causa)).thenReturn(exceptionConvertida);
        when(localizacaoGateway.getMensagem(keyMensagem)).thenReturn(mensagem);

        AplicacaoException exceptionResolvida = useCase.processarException(causa);
        assertThat(exceptionResolvida.getMessage()).isEqualTo(mensagem);
        assertThat(exceptionResolvida.getErrosValidacao())
                .extracting(ErroValidacao::getMensagem)
                .allMatch(s -> s.equals(mensagem), "Devem ter a mensagem '" + mensagem + "'");
    }

}