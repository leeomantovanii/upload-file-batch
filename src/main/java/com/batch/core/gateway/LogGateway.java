package com.batch.core.gateway;

import com.batch.core.models.DadosLog;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    /**
     * Método responsável por realizar a inserção de log manual
     *
     * @param log
     * @return
     */
    DadosLog inserirLog(DadosLog log);

    /**
     * Método responsável por remover log a partir do id do mesmo
     *
     * @param idLog
     */
    void removeLog(String idLog);

    /**
     * Método responsável por pesquisar um log especifico a partir do id
     *
     * @param idLog
     * @return
     */
    Optional<DadosLog> pesquisaPorId(String idLog);

    /**
     * Método responsável por pesquisar logs a partir do ip
     *
     * @param ip
     * @return
     */
    Optional<List<DadosLog>> pesquisarPorIp(String ip);

    /**
     * Método responsável por pesquisar logs a partir do ip e data
     * @param ip
     * @param dtInicio
     * @param dtFim
     * @return
     */
    Optional<List<DadosLog>> pesquisarPorIpEData(String ip, LocalDateTime dtInicio, LocalDateTime dtFim);

    /**
     * Método responsável por pesquisar logs a partir da data
     * @param dtInicio
     * @param dtFim
     * @return
     */
    Optional<List<DadosLog>> pesquisarPorData(LocalDateTime dtInicio, LocalDateTime dtFim);

    /**
     * Método responsável por atualizar log
     * @param dadosLog
     * @return
     */
    DadosLog atualizaLog(DadosLog dadosLog);

}
