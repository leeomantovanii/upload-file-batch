package com.batch.presenter.rest.controller;

import com.batch.core.InserirLogBatchUseCase;
import com.batch.presenter.rest.request.mapper.LogBatchRequestMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;
import teste.Podam;

import java.io.IOException;
import java.io.Reader;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Classe responsável por realizar testes unitários na {@link LogBatchController}
 *
 * @author Leonardo
 */
@ExtendWith(MockitoExtension.class)
class LogBatchControllerTest {

    @InjectMocks
    private LogBatchController controller;

    @Mock
    private LogBatchRequestMapper mapper;

    @Mock
    private InserirLogBatchUseCase useCase;

    @Test
    @DisplayName("Verifica se o serviço esta chamando a camada de negocio (useCase)")
    void verificaChamadaCamadaNegocio() throws IOException {
        MultipartFile fileLog = Podam.MOCKS.manufacturePojo(MultipartFile.class);
        Reader reader = Podam.MOCKS.manufacturePojo(Reader.class);

        when(mapper.requestToCore(fileLog)).thenReturn(reader);

        controller.inserirLogBatch(fileLog);

        verify(useCase, times(1)).processarArquivo(reader);

    }

}