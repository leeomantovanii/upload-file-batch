package com.batch.presenter.rest.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Objeto que representa os dados do log enviado por request no serviço de pesquisar log
 *
 * @author Leonardo
 */
@Builder
@Data
public class PesquisaLogRequest {

    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;

    private String ip;
}
