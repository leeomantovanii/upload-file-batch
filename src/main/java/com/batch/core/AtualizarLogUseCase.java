package com.batch.core;

import com.batch.core.anotacao.UseCase;
import com.batch.core.erro.NaoEncontradoException;
import com.batch.core.gateway.LogGateway;
import com.batch.core.models.DadosLog;
import lombok.RequiredArgsConstructor;

/**
 * UseCase responsável por possuir a regra de negocio de atualizar log
 *
 * @author Leonardo
 */
@UseCase
@RequiredArgsConstructor
public class AtualizarLogUseCase {

    private final LogGateway gateway;

    /**
     * Método responsável por verificar se o registro a ser atualizado existe, caso sim, atualizar, caso contrário, estourar exception
     *
     * @param dadosLog
     * @return
     */
    public DadosLog atualizarLog(DadosLog dadosLog) {
        pesquisarLog(dadosLog.getId());
        return gateway.atualizaLog(dadosLog);

    }

    private void pesquisarLog(String idLog) {
        gateway.pesquisaPorId(idLog).orElseThrow(() -> {
            return new NaoEncontradoException();
        });
    }
}
