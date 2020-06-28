package com.batch.dataprovider.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class LogId implements Serializable {

    @Column(name = "DATA")
    private LocalDateTime data;

    @Column(name = "USER_AGENT")
    private String userAgent;

    @Column(name = "IP")
    private String ip;

    @Column(name = "REQUEST")
    private String request;

    @Column(name = "STATUS")
    private Integer status;

}
