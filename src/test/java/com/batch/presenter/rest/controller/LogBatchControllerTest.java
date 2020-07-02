package com.batch.presenter.rest.controller;

import com.batch.core.*;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import teste.Podam;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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

    @Mock
    private DeletarLogUseCase deletarLogUseCase;

    @Mock
    private PesquisarLogUseCase pesquisarLogUseCase;

    @Mock
    private AtualizarLogUseCase atualizarLogUseCase;

    @Test
    @DisplayName("Verifica se o serviço esta chamando a camada de negocio (useCase) e se o status http é 200")
    void testaInserirLogBatch() throws IOException {
        MultipartFile fileLog = Podam.MOCKS.manufacturePojo(MultipartFile.class);
        Reader reader = Podam.MOCKS.manufacturePojo(Reader.class);

        when(mapper.requestBatchToCore(fileLog)).thenReturn(reader);

        ResponseEntity retorno = controller.inserirLogBatch(fileLog);

        verify(inserirLogBatchUseCase, times(1)).processarArquivo(reader);
        assertThat(retorno.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    }

    @Test
    @DisplayName("Verifica se o serviço esta chamando a camada de negocio (useCase) ao inserir log manual, se o objeto retornado é o esperado e se o status htpp é 200")
    void testaInserirLog() {
        LogRequest request = Podam.MOCKS.manufacturePojo(LogRequest.class);
        DadosLog core = Podam.MOCKS.manufacturePojo(DadosLog.class);
        LogResponse response = Podam.MOCKS.manufacturePojo(LogResponse.class);

        when(mapper.requestToCore(request)).thenReturn(core);
        when(mapper.coreToResponse(core)).thenReturn(response);
        when(inserirLogUseCase.insereLog(core)).thenReturn(core);

        ResponseEntity<LogResponse> retorno = controller.inserirLog(request);

        verify(inserirLogUseCase, times(1)).insereLog(core);
        assertThat(retorno.getBody()).isEqualTo(response);
        assertThat(retorno.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Verifica se o serviço esta chamando a camada de negocio (useCase) ao deletar log e se é retornado status http 200")
    void testaDeletarLog() {
        ResponseEntity retorno = controller.deletarLog("10");
        verify(deletarLogUseCase, times(1)).deletarLog("10");
        assertThat(retorno.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    @DisplayName("Verifica se o serviço esta chamando a camada de negocio (useCase), se retorna uma lista de objeto e se o status http é 200")
    void testaBuscarLogs() {
        List<DadosLog> retornoUseCase = Podam.MOCKS.manufacturePojo(ArrayList.class, DadosLog.class);
        List<LogResponse> retornoMaper = Podam.MOCKS.manufacturePojo(ArrayList.class, LogResponse.class);

        when(pesquisarLogUseCase.pesquisarLog(any(), any(), any())).thenReturn(retornoUseCase);
        when(mapper.listCoreToListResponse(any())).thenReturn(retornoMaper);

        ResponseEntity<List<LogResponse>> retorno = controller.buscarLogs("10", null, null);

        verify(pesquisarLogUseCase, times(1)).pesquisarLog("10", null, null);
        assertThat(retorno.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(retorno.getBody()).isEqualTo(retornoMaper);
    }

    @Test
    @DisplayName("Verifica se o serviço esta chamando a camada de negocio (useCase), se retorna o objeto atualizado e se o status http é 200")
    void testaAtualizaLog() {
        LogRequest request = Podam.MOCKS.manufacturePojo(LogRequest.class);
        DadosLog core = Podam.MOCKS.manufacturePojo(DadosLog.class);
        LogResponse response = Podam.MOCKS.manufacturePojo(LogResponse.class);

        when((mapper.requestToCore(request))).thenReturn(core);
        when(mapper.coreToResponse(core)).thenReturn(response);
        when(atualizarLogUseCase.atualizarLog(core)).thenReturn(core);

        ResponseEntity<LogResponse> retorno = controller.atualizaLog(request);

        verify(atualizarLogUseCase, times(1)).atualizarLog(core);
        assertThat(retorno.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(retorno.getBody()).isEqualTo(response);


    }

}