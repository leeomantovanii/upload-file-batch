package com.batch.presenter.rest.request.mapper;

import com.batch.core.models.DadosLog;
import com.batch.presenter.rest.request.LogRequest;
import com.batch.presenter.rest.response.LogResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import teste.Podam;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Classe responsável por realizar testes unitários na {@link LogBatchRequestMapper}
 *
 * @author Leonardo
 */
class LogBatchRequestMapperTest {

    LogBatchRequestMapper mapper = Mappers.getMapper(LogBatchRequestMapper.class);

    @DisplayName("Deve mapear request em core")
    @Test
    public void deveMapearParaCore() {
        LogRequest request = Podam.MOCKS.manufacturePojo(LogRequest.class);
        DadosLog core = mapper.requestToCore(request);

        assertThat(core.getData()).isEqualTo(request.getData());
        assertThat(core.getId()).isEqualTo(request.getId());
        assertThat(core.getIp()).isEqualTo(request.getIp());
        assertThat(core.getRequest()).isEqualTo(request.getRequest());
        assertThat(core.getStatus()).isEqualTo(request.getStatus());
        assertThat(core.getUserAgent()).isEqualTo(request.getUserAgent());
    }

    @DisplayName("Deve mapear core em response")
    @Test
    public void deveMapearParaResponse() {
        DadosLog core = Podam.MOCKS.manufacturePojo(DadosLog.class);
        LogResponse response = mapper.coreToResponse(core);

        assertThat(response.getData()).isEqualTo(core.getData());
        assertThat(response.getId()).isEqualTo(core.getId());
        assertThat(response.getIp()).isEqualTo(core.getIp());
        assertThat(response.getRequest()).isEqualTo(core.getRequest());
        assertThat(response.getStatus()).isEqualTo(core.getStatus());
        assertThat(response.getUserAgent()).isEqualTo(core.getUserAgent());
    }
}