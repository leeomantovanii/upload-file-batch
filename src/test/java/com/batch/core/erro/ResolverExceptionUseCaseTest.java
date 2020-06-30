package com.batch.core.erro;

import com.batch.core.erro.codigo.CodigoErroInfraestrutura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import teste.Podam;

import java.util.ArrayList;
import java.util.Collections;

import static com.batch.core.erro.codigo.CodigoErroNegocio.NAO_ENCONTRADO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;


class ResolverExceptionUseCaseTest {

    ResolverExceptionUseCase dataProvider;
    AplicacaoException.AplicacaoExceptionBuilder mockException;

    @BeforeEach
    public void beforeEach(){
        dataProvider = new ResolverExceptionUseCase(new ArrayList<>());
        mockException = Podam.MOCKS.manufacturePojo(AplicacaoException.AplicacaoExceptionBuilder.class);
    }

    @Test
    @DisplayName("Deve retornar erro desconhecido caso Exception recebida seja nula")
    public void nullDeveSerErroDesconhecido(){
        AplicacaoException exception = dataProvider.resolverException(null);
        assertThat(exception.getCodigoErro()).isEqualTo(CodigoErroInfraestrutura.DESCONHECIDO);
    }

    @Test
    @DisplayName("Deve retornar erro desconhecido caso não exista converter para a exception")
    public void desconhecidoCasoConverterInexistente(){
        AplicacaoException exception = dataProvider.resolverException(new RuntimeException());
        assertThat(exception.getCodigoErro()).isEqualTo(CodigoErroInfraestrutura.DESCONHECIDO);
    }

    @Test
    @DisplayName("Deve retornar exception convertida caso converter exista")
    public void deveUsarUmConverterExistenteParaConverter(){
        dataProvider = new ResolverExceptionUseCase(Collections.singletonList(new MockConverter()));
        Exception exception = new RuntimeException();
        assertThat(dataProvider.resolverException(exception).getCodigoErro()).isEqualTo(NAO_ENCONTRADO);
    }

    @Test
    @DisplayName("Deve retornar exception convertida caso converter de classe pai exista")
    public void deveRetornarExceptionConvertidaCasoConverterClassePaiExista(){
        dataProvider = new ResolverExceptionUseCase(Collections.singletonList(new MockPaiConverter()));
        Exception exception = new MockException();
        assertThat(dataProvider.resolverException(exception).getCodigoErro()).isEqualTo(NAO_ENCONTRADO);
    }

    @Test
    @DisplayName("Quando for aplicacao exception deve resolver ela mesma")
    public void deveRetornarMesmaExceptionCasoSejaAplicacoException(){
        dataProvider = new ResolverExceptionUseCase(Collections.emptyList());
        Exception exception = mock(AplicacaoException.class);
        assertThat(dataProvider.resolverException(exception)).isEqualTo(exception);
    }

    private class MockConverter extends  AplicacaoExceptionConverter<RuntimeException>{
        protected MockConverter () { super(RuntimeException.class); }

        @Override
        public AplicacaoException.AplicacaoExceptionBuilder converter(AplicacaoException.AplicacaoExceptionBuilder builder, RuntimeException exception){
            return mockException.codigoErro(NAO_ENCONTRADO);
        }
    }

    private static class MockException extends RuntimeException{

    }

    private class MockPaiConverter extends AplicacaoExceptionConverter<RuntimeException>{
        protected  MockPaiConverter() { super(RuntimeException.class); }

        @Override
        public AplicacaoException.AplicacaoExceptionBuilder converter (AplicacaoException.AplicacaoExceptionBuilder builder, RuntimeException exception){
            return mockException.codigoErro(NAO_ENCONTRADO);
        }
    }

}