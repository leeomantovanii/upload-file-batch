package com.batch.dataprovider;

import static org.assertj.core.api.Assertions.assertThat;

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
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Classe responsável por realizar testes de integração na {@link LogDataProvider}
 *
 * @author Leonardo
 */
@SpringBootTest
@AutoConfigureDataJpa
@ActiveProfiles("sandbox")
@Transactional
class LogDataProviderIntegrationTest {

    @Autowired
    private LogDataProvider dataProvider;

    @Autowired
    private LogRepository repository;

    @Test
    @DisplayName("Testa inserção de dados batch na tabela de log")
    @Sql("classpath:sql/log/PopulaTableLog.sql")
    public void testeInsercaoBatch() {
        List<DadosLog> dadosLog = Arrays.asList(Podam.MOCKS.manufacturePojo(DadosLog.class));

        List<LogTable> antesDoInsert = repository.findAll();
        dataProvider.inserirLogBatch(dadosLog);
        List<LogTable> depoisDoInsert = repository.findAll();

        assertThat(antesDoInsert.size()).isEqualTo(1);
        assertThat(depoisDoInsert.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Testa inserção de log individual. Verifica se o objeto salvo é retornado")
    @Sql("classpath:sql/log/PopulaTableLog.sql")
    public void testeInsercaoLog() {
        DadosLog dadosLog = Podam.MOCKS.manufacturePojo(DadosLog.class);

        List<LogTable> antesDoInsert = repository.findAll();
        DadosLog retorno = dataProvider.inserirLog(dadosLog);
        List<LogTable> depoisDoInsert = repository.findAll();

        assertThat(antesDoInsert.size()).isEqualTo(1);
        assertThat(depoisDoInsert.size()).isEqualTo(2);
        assertThat(retorno).isNotNull();
    }

    @Test
    @DisplayName("Testa o metodo que remove log. É esperado sucesso")
    @Sql("classpath:sql/log/PopulaTableLog.sql")
    public void testeDeleteLog() {
        List<LogTable> antesDeRemover = repository.findAll();
        dataProvider.removeLog(mockLog().getId());
        List<LogTable> depoisDeRemover = repository.findAll();

        assertThat(antesDeRemover.size()).isEqualTo(1);
        assertThat(depoisDeRemover.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("Testa o metodo que pesquisa log por id. É esperado sucesso")
    @Sql("classpath:sql/log/PopulaTableLog.sql")
    public void testePesquisaLogPorIdSucesso() {
        Optional<DadosLog> retorno = dataProvider.pesquisaPorId(mockLog().getId());
        assertThat(retorno.get()).isEqualTo(mockLog());
    }

    @Test
    @DisplayName("Testa o metodo que pesquisa log por id. É esperado que não seja encontrado o log")
    @Sql("classpath:sql/log/PopulaTableLog.sql")
    public void testePesquisaLogPorIdErro() {
        DadosLog mockDados = mockLog();
        mockDados.setId("teste");
        Optional<DadosLog> retorno = dataProvider.pesquisaPorId(mockDados.getId());

        assertThat(retorno).isEqualTo(Optional.empty());
    }

    @Test
    @DisplayName("Testa o metodo que pesquisa log por ip. É esperado sucesso")
    @Sql("classpath:sql/log/PopulaTableLog.sql")
    public void testePesquisaLogPorIpSucesso() {
        Optional<List<DadosLog>> listRetorno = dataProvider.pesquisarPorIp(mockLog().getIp());
        assertThat(listRetorno).isNotNull();
    }

    @Test
    @DisplayName("Testa o metodo que pesquisa log por ip. É esperado que não seja encontrado o log")
    @Sql("classpath:sql/log/PopulaTableLog.sql")
    public void testePesquisaLogPorIpErro() {
        Optional<List<DadosLog>> listRetorno = dataProvider.pesquisarPorIp("11.22.34.5");
        assertThat(listRetorno).isEqualTo(Optional.empty());
    }

    @Test
    @DisplayName("Testa o metodo que pesquisa log por ip e data. É esperado sucesso")
    @Sql("classpath:sql/log/PopulaTableLog.sql")
    public void testePesquisaLogPorIpEDataSucesso() {
        Optional<List<DadosLog>> listRetorno = dataProvider.pesquisarPorIpEData(mockLog().getIp(), LocalDateTime.parse("2019-01-01T00:00:00.000"), LocalDateTime.parse("2021-01-01T00:00:00.000"));
        assertThat(listRetorno).isNotNull();
    }

    @Test
    @DisplayName("Testa o metodo que pesquisa log por ip e data. É esperado que não seja encontrado o log")
    @Sql("classpath:sql/log/PopulaTableLog.sql")
    public void testePesquisaLogPorIpEDataErro() {
        Optional<List<DadosLog>> listRetorno = dataProvider.pesquisarPorIpEData(mockLog().getIp(), LocalDateTime.parse("2021-01-01T00:00:00.000"), LocalDateTime.parse("2022-01-01T00:00:00.000"));
        assertThat(listRetorno).isEqualTo(Optional.empty());
    }

    @Test
    @DisplayName("Testa o metodo que pesquisa log por data. É esperado sucesso")
    @Sql("classpath:sql/log/PopulaTableLog.sql")
    public void testePesquisaLogPorDataSucesso() {
        Optional<List<DadosLog>> listRetorno = dataProvider.pesquisarPorData(LocalDateTime.parse("2019-01-01T00:00:00.000"), LocalDateTime.parse("2021-01-01T00:00:00.000"));
        assertThat(listRetorno).isNotNull();
    }

    @Test
    @DisplayName("Testa o metodo que pesquisa log por data. É esperado que não seja encontrado o log")
    @Sql("classpath:sql/log/PopulaTableLog.sql")
    public void testePesquisaLogPorDataErro() {
        Optional<List<DadosLog>> listRetorno = dataProvider.pesquisarPorData(LocalDateTime.parse("2021-01-01T00:00:00.000"), LocalDateTime.parse("2022-01-01T00:00:00.000"));
        assertThat(listRetorno).isEqualTo(Optional.empty());
    }

    @Test
    @DisplayName("Testa o metodo que atualiza log. É esperado sucesso")
    @Sql("classpath:sql/log/PopulaTableLog.sql")
    public void testeAtualizaLog() {
        DadosLog log = mockLog();
        log.setUserAgent("teste");
        log.setIp("2.2.2.2.2");

        DadosLog retorno = dataProvider.atualizaLog(log);
        assertThat(retorno).isEqualTo(log);
    }

    private DadosLog mockLog() {
        return DadosLog.builder()
                .id("13fd87c8-eb98-4d73-9f1e-6e63ca15a753")
                .status(200)
                .request("teste")
                .ip("1.1.1.1.1.1")
                .userAgent("Leonardo")
                .data(LocalDateTime.parse("2020-01-01T00:00:00.000"))
                .build();
    }
}