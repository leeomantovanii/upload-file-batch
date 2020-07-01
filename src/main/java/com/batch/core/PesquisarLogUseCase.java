package com.batch.core;

import com.batch.core.anotacao.UseCase;
import com.batch.core.erro.NaoEncontradoException;
import com.batch.core.erro.SintaxeErradaException;
import com.batch.core.gateway.LogGateway;
import com.batch.core.models.DadosLog;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * UseCase responsável por possuir a regra de negocio de pesquisar log
 *
 * @author Leonardo
 */
@UseCase
@RequiredArgsConstructor
public class PesquisarLogUseCase {

    private final LogGateway gateway;

    /**
     * Método responsável por fazer a verificação de qual tipo de filtro o usuário quer
     * Filtros existentes: por todos os campos (ip, dtInicio e dtFim), por data (dtInicio e dtFim)
     * e apenas por id. Caso não for nenhum desses filtros, deve estourar exception
     *
     * @param ip
     * @param dtInicio
     * @param dtFim
     * @return
     */
    public List<DadosLog> pesquisarLog(String ip, LocalDateTime dtInicio, LocalDateTime dtFim) {

        if (ip != null && dtInicio != null && dtFim != null) {
            return gateway.pesquisarPorIpEData(ip, dtInicio, dtFim).orElseThrow(() -> {
                return new NaoEncontradoException();
            });
        } else if (ip != null && dtInicio == null && dtFim == null) {
            return gateway.pesquisarPorIp(ip).orElseThrow(() -> {
                return new NaoEncontradoException();
            });
        } else if (ip == null && dtInicio != null && dtFim != null) {
            return gateway.pesquisarPorData(dtInicio, dtFim).orElseThrow(() -> {
                return new NaoEncontradoException();
            });
        } else {
            throw new SintaxeErradaException();
        }

    }

}
