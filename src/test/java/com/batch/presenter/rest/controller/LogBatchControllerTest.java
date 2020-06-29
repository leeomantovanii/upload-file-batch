package com.batch.presenter.rest.controller;

import com.batch.core.InserirLogBatchUseCase;
import com.batch.core.InserirLogUseCase;
import com.batch.core.models.DadosLog;
import com.batch.presenter.rest.request.LogRequest;
import com.batch.presenter.rest.request.mapper.LogBatchRequestMapper;
import com.batch.presenter.rest.response.LogResponse;
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
    private InserirLogBatchUseCase inserirLogBatchUseCase;

    @Mock
    private InserirLogUseCase inserirLogUseCase;

    @Test
    @DisplayName("Verifica se o serviço esta chamando a camada de negocio (useCase)")
    void verificaChamadaCamadaNegocioinserirLogBatch() throws IOException {
        MultipartFile fileLog = Podam.MOCKS.manufacturePojo(MultipartFile.class);
        Reader reader = Podam.MOCKS.manufacturePojo(Reader.class);

        when(mapper.requestBatchToCore(fileLog)).thenReturn(reader);

        controller.inserirLogBatch(fileLog);

        verify(inserirLogBatchUseCase, times(1)).processarArquivo(reader);

    }

    @Test
    @DisplayName("Verifica se o serviço esta chamando a camada de negocio (useCase) ao inserir log manual")
    void verificaChamadaCamadaNegocioinserirLog() throws IOException {
        LogRequest request = Podam.MOCKS.manufacturePojo(LogRequest.class);
        DadosLog core = Podam.MOCKS.manufacturePojo(DadosLog.class);
        LogResponse response = Podam.MOCKS.manufacturePojo(LogResponse.class);

        when(mapper.requestToCore(request)).thenReturn(core);
        when(mapper.coreToResponse(core)).thenReturn(response);
        when(inserirLogUseCase.insereLog(core)).thenReturn(core);

        controller.inserirLog(request);

        verify(inserirLogUseCase, times(1)).insereLog(core);

    }

}