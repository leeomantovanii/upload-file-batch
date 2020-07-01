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
import static org.mockito.Mockito.*;

/**
 * Classe responsável por realizar testes unitários na {@link DeletarLogUseCase}
 *
 * @author Leonardo
 */
@ExtendWith(MockitoExtension.class)
class DeletarLogUseCaseTest {

    @InjectMocks
    private DeletarLogUseCase useCase;

    @Mock
    private LogGateway gateway;

    @Test
    @DisplayName("Verifica se a useCase esta chamando o dataProvider corretamente. ")
    void verificaDeletarLog()  {
        DadosLog logExistente = Podam.MOCKS.manufacturePojo(DadosLog.class);
        when(gateway.pesquisaPorId(any())).thenReturn(Optional.ofNullable(logExistente));
        useCase.deletarLog(any());

        verify(gateway, times(1)).removeLog(any());
    }

    @Test
    @DisplayName("É esperado que nao seja encontrado o log a ser removido. É esperado NaoEncontradoException ")
    void verificaDeletarLogErro()  {
        when(gateway.pesquisaPorId(any())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> useCase.deletarLog(any())).isInstanceOf(NaoEncontradoException.class);
    }

}