package com.batch.core.gateway;

import com.batch.core.models.DadosLog;

import java.util.List;

/**
 * Gateway responsável por prover acesso a camada de dataProvider, que realiza as operações de CRUD de log
 *
 * @author Leonardo
 */
public interface LogGateway {

    /**
     * Método responsável por realizar a inserção dos logs em batch
     *
     * @param listLog
     */
    void inserirLogBatch(List<DadosLog> listLog);

}
