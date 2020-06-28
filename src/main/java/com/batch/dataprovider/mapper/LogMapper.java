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
     * Método responsável por converter objetos do tipo core em table
     * @param listLog
     * @return
     */
    default List<LogTable> coreToTable(List<DadosLog> listLog) {
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
}
