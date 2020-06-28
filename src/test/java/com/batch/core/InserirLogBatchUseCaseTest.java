package com.batch.core;

import com.batch.core.gateway.LogGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.StringReader;

import static org.mockito.Mockito.*;


/**
 * Classe responsável por realizar testes unitários na {@link InserirLogBatchUseCase}
 *
 * @author Leonardo
 */
@ExtendWith(MockitoExtension.class)
class InserirLogBatchUseCaseTest {

    @InjectMocks
    private InserirLogBatchUseCase useCase;

    @Mock
    private LogGateway gateway;

    @Test
    @DisplayName("Verifica se a useCase esta chamando o dataProvider corretamente")
    void verificaChamadaDataProvider() throws IOException {
        useCase.processarArquivo(mockLogReader());
        verify(gateway, times(1)).inserirLogBatch(any());
    }

    private StringReader mockLogReader() {
        StringBuilder sb = new StringBuilder("");
        sb.append("2019-01-01 00:00:11.763|192.168.234.82|'GET / HTTP/1.1'|200|'teste1' \n");
        sb.append("2019-01-01 00:00:21.164|192.168.234.82|'GET / HTTP/1.1'|200|'teste2' \n");
        sb.append("2019-01-01 00:00:23.003|192.168.169.194|'GET / HTTP/1.1'|200|'teste3' \n");
        sb.append("2019-01-01 00:00:40.554|192.168.234.82|'GET / HTTP/1.1'|200|'dddddd' \n");

        return new StringReader(sb.toString());
    }


}