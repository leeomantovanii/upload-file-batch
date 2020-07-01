package com.batch.dataprovider.mapper;

import com.batch.core.models.DadosLog;
import com.batch.dataprovider.entity.LogTable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import teste.Podam;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Classe responsável por realizar testes unitários na {@link LogMapper}
 *
 * @author Leonardo
 */
class LogMapperTest {

    LogMapper mapper = Mappers.getMapper(LogMapper.class);

    @DisplayName("Deve mapear core em table")
    @Test
    public void deveMapearParaTable(){
        DadosLog core = Podam.MOCKS.manufacturePojo(DadosLog.class);
        LogTable table = mapper.coreToTable(core);

        assertThat(table.getData()).isEqualTo(core.getData());
        assertThat(table.getId()).isEqualTo(core.getId());
        assertThat(table.getIp()).isEqualTo(core.getIp());
        assertThat(table.getRequest()).isEqualTo(core.getRequest());
        assertThat(table.getStatus()).isEqualTo(core.getStatus());
        assertThat(table.getUserAgent()).isEqualTo(core.getUserAgent());
    }

    @DisplayName("Deve mapear table em core")
    @Test
    public void deveMapearParaCore(){
        LogTable table = Podam.MOCKS.manufacturePojo(LogTable.class);
        DadosLog core = mapper.tableToCore(table);

        assertThat(core.getData()).isEqualTo(table.getData());
        assertThat(core.getId()).isEqualTo(table.getId());
        assertThat(core.getIp()).isEqualTo(table.getIp());
        assertThat(core.getRequest()).isEqualTo(table.getRequest());
        assertThat(core.getStatus()).isEqualTo(table.getStatus());
        assertThat(core.getUserAgent()).isEqualTo(table.getUserAgent());
    }

}