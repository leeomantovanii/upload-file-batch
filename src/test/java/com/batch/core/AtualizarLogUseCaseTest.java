package com.batch.core;

import com.batch.core.erro.NaoEncontradoException;
import com.batch.core.gateway.LogGateway;
import com.batch.core.models.DadosLog;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import teste.Podam;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

/**
 * Classe responsável por realizar testes unitários na {@link AtualizarLogUseCase}
 *
 * @author Leonardo
 */
@ExtendWith(MockitoExtension.class)
class AtualizarLogUseCaseTest {

    @InjectMocks
    private AtualizarLogUseCase useCase;

    @Mock
    private LogGateway gateway;

    @Test
    @DisplayName("Verifica se a useCase esta chamando o dataProvider corretamente. ")
    void verificaAtualizaLog()  {
        DadosLog logParaAtualizar = Podam.MOCKS.manufacturePojo(DadosLog.class);
        DadosLog logExistente = Podam.MOCKS.manufacturePojo(DadosLog.class);
        when(gateway.pesquisaPorId(any())).thenReturn(Optional.ofNullable(logExistente));
        useCase.atualizarLog(logParaAtualizar);

        verify(gateway, times(1)).atualizaLog(any());
    }

    @Test
    @DisplayName("É esperado que nao seja encontrado o log a ser removido. É esperado NaoEncontradoException ")
    void verificaAtualizaLogErro()  {
        DadosLog logParaAtualizar = Podam.MOCKS.manufacturePojo(DadosLog.class);
        when(gateway.pesquisaPorId(any())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> useCase.atualizarLog(logParaAtualizar)).isInstanceOf(NaoEncontradoException.class);
    }

}