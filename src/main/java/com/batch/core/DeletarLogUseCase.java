package com.batch.core;

import com.batch.core.anotacao.UseCase;
import com.batch.core.erro.NaoEncontradoException;
import com.batch.core.gateway.LogGateway;
import lombok.RequiredArgsConstructor;

/**
 * UseCase responsável por possuir a regra de negocio de deletar log
 *
 * @author Leonardo
 */
@UseCase
@RequiredArgsConstructor
public class DeletarLogUseCase {

    private final LogGateway gateway;

    /**
     * Método responsável por verificar se o log a ser excluido existe, caso sim,
     * chamar o método responsável por fazer o delete
     *
     * @param idLog
     */
    public void deletarLog(String idLog) {
        pesquisarLog(idLog);
        gateway.removeLog(idLog);
    }

    private void pesquisarLog(String idLog) {
        gateway.pesquisaPorId(idLog).orElseThrow(() -> {
            return new NaoEncontradoException();
        });
    }
}
