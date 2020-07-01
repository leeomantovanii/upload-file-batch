package com.batch.dataprovider.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_LOG")
public class LogTable {

    @Id
    private String id;

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

    @PrePersist
    public void prePersist(){
        setId(UUID.randomUUID().toString());
    }


}
