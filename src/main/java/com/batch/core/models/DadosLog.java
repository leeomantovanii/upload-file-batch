package com.batch.core.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Classe que representa os atributos do log
 *
 * @author Leonardo
 */
@Builder
@Data
public class DadosLog {

    private String id;

    private LocalDateTime data;

    private String ip;

    private String request;

    private Integer status;

    private String userAgent;
}
