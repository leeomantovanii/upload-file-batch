package com.batch.dataprovider;

import com.batch.core.gateway.LogGateway;
import com.batch.core.models.DadosLog;
import com.batch.dataprovider.mapper.LogMapper;
import com.batch.dataprovider.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * DataProvider responsável por prover acesso ao repositório que realiza as operações na tabela do log
 *
 * @author Leonardo
 */
@Component
@RequiredArgsConstructor
public class LogDataProvider implements LogGateway {

    private final LogRepository repository;

    private final LogMapper mapper;

    @Override
    public void inserirLogBatch(List<DadosLog> listLog) {
        repository.saveAll(mapper.coreToTable(listLog));

    }
}
