package com.batch.presenter.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDateTime;

/**
 * Objeto que representa os dados do log enviado por request
 *
 * @author Leonardo
 */
@Builder
@Data
public class LogRequest {

    @NotNull
    @JsonProperty("data")
    private LocalDateTime data;

    @NotNull
    @JsonProperty("user_agent")
    private String userAgent;

    @NotNull
    @JsonProperty("ip")
    private String ip;

    @NotNull
    @JsonProperty("request")
    private String request;

    @NotNull
    @JsonProperty("status")
    private Integer status;
}
