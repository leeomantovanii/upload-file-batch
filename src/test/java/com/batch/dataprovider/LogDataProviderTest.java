package com.batch.dataprovider;

import static  org.assertj.core.api.Assertions.assertThat;
import com.batch.core.models.DadosLog;
import com.batch.dataprovider.entity.LogTable;
import com.batch.dataprovider.repository.LogRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import teste.Podam;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureDataJpa
@ActiveProfiles("sandbox")
@Transactional
class LogDataProviderTest {

    @Autowired
    private LogDataProvider dataProvider;

    @Autowired
    private LogRepository repository;

    @Test
    @DisplayName("Testa inserção de dados na tabela de log")
    @Sql("classpath:sql/log/PopulaTableLog.sql")
    public void teste() {
        List<DadosLog> dadosLog = Arrays.asList(Podam.MOCKS.manufacturePojo(DadosLog.class));

        List<LogTable> antesDoInsert = repository.findAll();
        dataProvider.inserirLogBatch(dadosLog);
        List<LogTable> depoisDoInsert = repository.findAll();

        assertThat(antesDoInsert.size()).isEqualTo(1);
        assertThat(depoisDoInsert.size()).isEqualTo(2);

    }
}