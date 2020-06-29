package com.batch.presenter.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Objeto que representa os dados do log retornado no response
 *
 * @author Leonardo
 */
@Builder
@Data
public class LogResponse {

    @JsonProperty("data")
    private LocalDateTime data;

    @JsonProperty("user_agent")
    private String userAgent;

    @JsonProperty("ip")
    private String ip;

    @JsonProperty("request")
    private String request;

    @JsonProperty("status")
    private Integer status;
}
