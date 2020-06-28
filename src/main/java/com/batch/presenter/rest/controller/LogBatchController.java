package com.batch.presenter.rest.controller;

import com.batch.core.InserirLogBatchUseCase;
import com.batch.presenter.rest.request.mapper.LogBatchRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Controller responsável por realizar operações CRUD de log
 *
 * @author Leonardo
 */

@RestController
@RequestMapping("/v1/logBatch")
@RequiredArgsConstructor
public class LogBatchController {

    private final LogBatchRequestMapper mapper;

    private final InserirLogBatchUseCase inserirLogBatchUseCase;

    /**
     * Serviço responsável por fazer inserção em batch
     * @param arquivoLog
     * @return
     */
    @PostMapping
    public ResponseEntity inserirLogBatch(@RequestParam("arquivoLog") MultipartFile arquivoLog) {

        try {
            inserirLogBatchUseCase.processarArquivo(mapper.requestToCore(arquivoLog));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
