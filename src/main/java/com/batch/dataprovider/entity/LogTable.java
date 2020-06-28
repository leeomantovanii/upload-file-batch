package com.batch.dataprovider.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_LOG")
public class LogTable {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "ID_LOG")
//    private Long idLog;

//    @Column(name = "DATA")
//    private LocalDateTime data;
//
//    @Column(name = "IP")
//    private String ip;
//
//    @Column(name = "REQUEST")
//    private String request;
//
//    @Column(name = "STATUS")
//    private Integer status;
//
//    @Id
//    @Column(name = "USER_AGENT")
//    private String userAgent;


    @EmbeddedId
    private LogId id;

//    @Column(name = "IP")
//    private String ip;
//
//    @Column(name = "REQUEST")
//    private String request;
//
//    @Column(name = "STATUS")
//    private Integer status;


}
