package com.batch.core;

import com.batch.core.gateway.LogGateway;
import com.batch.core.models.DadosLog;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import teste.Podam;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Classe responsável por realizar testes unitários na {@link InserirLogUseCase}
 *
 * @author Leonardo
 */
@ExtendWith(MockitoExtension.class)
class InserirLogUseCaseTest {

    @InjectMocks
    private InserirLogUseCase useCase;

    @Mock
    private LogGateway gateway;

    @Test
    @DisplayName("Verifica se a useCase esta chamando o dataProvider corretamente")
    void verificaChamadaDataProvider() throws IOException {
        DadosLog log = Podam.MOCKS.manufacturePojo(DadosLog.class);

        when(gateway.inserirLog(log)).thenReturn(log);

        useCase.insereLog(log);
        verify(gateway, times(1)).inserirLog(log);
    }
}