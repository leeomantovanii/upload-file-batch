package com.batch.core;

import com.batch.core.erro.NaoEncontradoException;
import com.batch.core.erro.SintaxeErradaException;
import com.batch.core.gateway.LogGateway;
import com.batch.core.models.DadosLog;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import teste.Podam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Classe responsável por realizar testes unitários na {@link PesquisarLogUseCase}
 *
 * @author Leonardo
 */
@ExtendWith(MockitoExtension.class)
class PesquisarLogUseCaseTest {

    @InjectMocks
    private PesquisarLogUseCase useCase;

    @Mock
    private LogGateway gateway;

    @Test
    @DisplayName("Testa pesquisa por id e data. É esperado que chame uma vez o dataProvider e que seja encontrado informações")
    void verificaPesquisaPorIdEDataSucesso()  {
        List<DadosLog> retorno = Podam.MOCKS.manufacturePojo(ArrayList.class, DadosLog.class);

        when(gateway.pesquisarPorIpEData(any(), any(), any())).thenReturn(Optional.ofNullable(retorno));

        List<DadosLog> resultado = useCase.pesquisarLog("123", LocalDateTime.now(), LocalDateTime.now());

        assertThat(resultado).isEqualTo(retorno);
        verify(gateway, times(1)).pesquisarPorIpEData(any(), any(), any());
    }

    @Test
    @DisplayName("Testa pesquisa por id e data. É esperado que chame uma vez o dataProvider e que não seja encontrado logs")
    void verificaPesquisaPorIdEDataErro()  {
        when(gateway.pesquisarPorIpEData(any(), any(), any())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> useCase.pesquisarLog("123", LocalDateTime.now(),LocalDateTime.now())).isInstanceOf(NaoEncontradoException.class);
    }

    @Test
    @DisplayName("Testa pesquisa por data. É esperado que chame uma vez o dataProvider e que seja encontrado logs")
    void verificaPesquisaPorDataSucesso()  {
        List<DadosLog> retorno = Podam.MOCKS.manufacturePojo(ArrayList.class, DadosLog.class);

        when(gateway.pesquisarPorData(any(), any())).thenReturn(Optional.ofNullable(retorno));

        List<DadosLog> resultado = useCase.pesquisarLog(null, LocalDateTime.now(), LocalDateTime.now());

        assertThat(resultado).isEqualTo(retorno);
        verify(gateway, times(1)).pesquisarPorData(any(), any());
    }

    @Test
    @DisplayName("Testa pesquisa por data. É esperado que chame uma vez o dataProvider e que não seja encontrado logs")
    void verificaPesquisaPorDataErro()  {
        when(gateway.pesquisarPorData(any(), any())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> useCase.pesquisarLog(null, LocalDateTime.now(),LocalDateTime.now())).isInstanceOf(NaoEncontradoException.class);
    }

    @Test
    @DisplayName("Testa pesquisa por ip. É esperado que chame uma vez o dataProvider e que seja encontrado logs")
    void verificaPesquisaPorIpSucesso()  {
        List<DadosLog> retorno = Podam.MOCKS.manufacturePojo(ArrayList.class, DadosLog.class);

        when(gateway.pesquisarPorIp(any())).thenReturn(Optional.ofNullable(retorno));

        List<DadosLog> resultado = useCase.pesquisarLog("123", null, null);

        assertThat(resultado).isEqualTo(retorno);
        verify(gateway, times(1)).pesquisarPorIp(any());
    }

    @Test
    @DisplayName("Testa pesquisa por ip. É esperado que chame uma vez o dataProvider e que não seja encontrado logs")
    void verificaPesquisaPorIpErro()  {
        when(gateway.pesquisarPorIp(any())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> useCase.pesquisarLog("123", null, null)).isInstanceOf(NaoEncontradoException.class);
    }

    @Test
    @DisplayName("Testa entrada de informações erradas. É esperado SintaxeErradaException")
    void verificaPesquisaEntradaIncorreta()  {
        assertThatThrownBy(() -> useCase.pesquisarLog(null, null, null)).isInstanceOf(SintaxeErradaException.class);
    }
}