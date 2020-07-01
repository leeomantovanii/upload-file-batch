package com.batch.presenter.rest.controller;

import com.batch.core.*;
import com.batch.core.models.DadosLog;
import com.batch.presenter.rest.request.LogRequest;
import com.batch.presenter.rest.request.mapper.LogBatchRequestMapper;
import com.batch.presenter.rest.response.LogResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Controller responsável por realizar operações CRUD de log
 *
 * @author Leonardo
 */

@RestController
@RequestMapping("/v1/log")
@RequiredArgsConstructor
public class LogBatchController {

    private final LogBatchRequestMapper mapper;

    private final InserirLogBatchUseCase inserirLogBatchUseCase;

    private final InserirLogUseCase inserirLogUseCase;

    private final DeletarLogUseCase deletarLogUseCase;

    private final PesquisarLogUseCase pesquisarLogUseCase;

    private final AtualizarLogUseCase atualizarLogUseCase;

    /**
     * Serviço responsável por fazer inserção em batch
     *
     * @param arquivoLog
     * @return
     */
    @PostMapping(value = "/batch")
    public ResponseEntity inserirLogBatch(@RequestParam("arquivoLog") MultipartFile arquivoLog) {

        try {
            inserirLogBatchUseCase.processarArquivo(mapper.requestBatchToCore(arquivoLog));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Serviço responsável por fazer inserção de log manual
     *
     * @param request
     * @return
     */
    @PostMapping
    public ResponseEntity<LogResponse> inserirLog(@RequestBody @Valid LogRequest request) {

        DadosLog retorno = inserirLogUseCase.insereLog(mapper.requestToCore(request));

        return new ResponseEntity(mapper.coreToResponse(retorno), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletar/{id_log}")
    public ResponseEntity deletarLog(@PathVariable("id_log") final String idLog) {

        deletarLogUseCase.deletarLog(idLog);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<LogResponse>> buscarLogs(@RequestParam(required = false, name = "ip") String ip,
                                                        @RequestParam(required = false, name = "dt_inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dtInicio,
                                                        @RequestParam(required = false, name = "dt_fim")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dtFim) {

        List<DadosLog> result = pesquisarLogUseCase.pesquisarLog(ip, dtInicio, dtFim);

        return new ResponseEntity(mapper.listCoreToListResponse(result), HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<LogResponse> atualizaLog(@RequestBody @Valid LogRequest request) {

        DadosLog retorno = atualizarLogUseCase.atualizarLog(mapper.requestToCore(request));

        return new ResponseEntity(mapper.coreToResponse(retorno), HttpStatus.OK);
    }
}
