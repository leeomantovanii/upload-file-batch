package com.batch.core.gateway;

import com.batch.core.models.DadosLog;

import java.util.List;

public interface LogGateway {

    void inserirLogBatch (List<DadosLog> listLog);

}
