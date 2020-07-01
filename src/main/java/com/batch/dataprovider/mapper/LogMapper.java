package com.batch.dataprovider.mapper;

import com.batch.core.models.DadosLog;
import com.batch.dataprovider.entity.LogTable;
import org.mapstruct.Mapper;

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
    List<LogTable> listCoreTolistTable(List<DadosLog> listLog);

    /**
     * Método responsável por converter objeto do tipo core em table
     *
     * @param log
     * @return
     */
    LogTable coreToTable(DadosLog log);

    /**
     * Método responsável por converter objeto do tipo table em core
     *
     * @return
     */
    DadosLog tableToCore(LogTable table);

    /**
     * Método responsável por converter objeto do tipo ListTable em ListCore
     * @param listTable
     * @return
     */
    List<DadosLog> listTableToListCore(List<LogTable> listTable);
}
