package com.batch.core;

import com.batch.core.anotacao.UseCase;
import com.batch.core.gateway.LogGateway;
import com.batch.core.models.DadosLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * UseCase responsável por possuir a regra de negocio da inserção do log
 *
 * @author Leonardo
 */
@UseCase
@RequiredArgsConstructor
public class InserirLogUseCase {

    private final LogGateway gateway;

    public DadosLog insereLog(DadosLog log) {
        return gateway.inserirLog(log);
    }
}
