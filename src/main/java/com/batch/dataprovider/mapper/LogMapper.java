package com.batch.dataprovider.mapper;

import com.batch.core.models.DadosLog;
import com.batch.dataprovider.entity.LogId;
import com.batch.dataprovider.entity.LogTable;
import org.mapstruct.Mapper;
import java.util.ArrayList;
import java.util.List;

/**
 * Mapper responsável por converter objeto do tipo request em core e objeto do tipo core em response
 *
 * @author Leonardo
 */
@Mapper(componentModel = "spring")
public interface LogMapper {

    /**
     * Método responsável por converter objetos do tipo listCore em listTable
     *
     * @param listLog
     * @return
     */
    default List<LogTable> listCoreTolistTable(List<DadosLog> listLog) {
        List<LogTable> retorno = new ArrayList<>();

        listLog.forEach(log -> {
            retorno.add(LogTable.builder()
                    .id(LogId.builder()
                            .data(log.getData())
                            .userAgent(log.getUserAgent())
                            .ip(log.getIp())
                            .request(log.getRequest())
                            .status(log.getStatus())
                            .build())
                    .build());
        });

        return retorno;
    }

    /**
     * Método responsável por converter objeto do tipo core em table
     *
     * @param log
     * @return
     */
    default LogTable coreToTable(DadosLog log) {
        return LogTable.builder()
                .id(LogId.builder()
                        .data(log.getData())
                        .userAgent(log.getUserAgent())
                        .ip(log.getIp())
                        .request(log.getRequest())
                        .status(log.getStatus())
                        .build())
                .build();
    }

    /**
     * Método responsável por converter objeto do tipo table em core
     *
     * @return
     */
    default DadosLog tableToCore(LogTable table) {
        return DadosLog.builder()
                .data(table.getId().getData())
                .userAgent(table.getId().getUserAgent())
                .ip(table.getId().getIp())
                .request(table.getId().getRequest())
                .status(table.getId().getStatus())
                .build();
    }
}
