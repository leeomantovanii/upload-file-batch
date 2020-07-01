package com.batch.dataprovider;

import com.batch.core.gateway.LogGateway;
import com.batch.core.models.DadosLog;
import com.batch.dataprovider.mapper.LogMapper;
import com.batch.dataprovider.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

        repository.saveAll(mapper.listCoreTolistTable(listLog));
    }

    @Override
    public DadosLog inserirLog(DadosLog log) {

        return mapper.tableToCore(repository.save(mapper.coreToTable(log)));
    }

    @Override
    public void removeLog(String idLog) {

        repository.deleteById(idLog);
    }

    @Override
    public Optional<DadosLog> pesquisaPorId(String idLog) {
        return repository.findById(idLog).map(mapper::tableToCore);
    }

    @Override
    public Optional<List<DadosLog>> pesquisarPorIp(String ip) {
        return repository.findByIp(ip).map(mapper::listTableToListCore);
    }

    @Override
    public Optional<List<DadosLog>> pesquisarPorIpEData(String ip, LocalDateTime dtInicio, LocalDateTime dtFim) {
        return repository.findByIpAndDataBetween(ip, dtInicio, dtFim).map(mapper::listTableToListCore);
    }

    @Override
    public Optional<List<DadosLog>> pesquisarPorData(LocalDateTime dtInicio, LocalDateTime dtFim) {
        return repository.findByDataBetween(dtInicio, dtFim).map(mapper::listTableToListCore);
    }

    @Override
    public DadosLog atualizaLog(DadosLog dadosLog) {
        return mapper.tableToCore(repository.save(mapper.coreToTable(dadosLog)));
    }
}
