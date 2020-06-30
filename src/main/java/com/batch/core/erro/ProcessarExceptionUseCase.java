package com.batch.core.erro;

import com.batch.core.anotacao.UseCase;
import com.batch.core.gateway.LocalizacaoGateway;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe responsável por processar uma exception da aplicação. Converte mensagens para a lingua desejada e redireciona para o sistema de log.
 *
 * @author Leonardo
 */
@UseCase
public class ProcessarExceptionUseCase {

    private final ResolverExceptionUseCase resolverExceptionUseCase;
    private final LocalizacaoGateway localizacaoGateway;

    public ProcessarExceptionUseCase(ResolverExceptionUseCase resolverExceptionUseCase, LocalizacaoGateway localizacaoGateway){
        this.resolverExceptionUseCase = resolverExceptionUseCase;
        this.localizacaoGateway = localizacaoGateway;
    }

    public AplicacaoException processarException(Throwable ex){
        AplicacaoException exceptionResolvida = resolverExceptionUseCase.resolverException(ex);

        return AplicacaoException.builder()
                .message(localizacaoGateway.getMensagem(exceptionResolvida.getMessage()))
                .cause(ex)
                .codigoErro(exceptionResolvida.getCodigoErro())
                .errosValidacao(buildErrosValidacao(exceptionResolvida))
                .build();
    }

    private List<ErroValidacao> buildErrosValidacao(AplicacaoException exceptionResolvida){
        return exceptionResolvida.getErrosValidacao()
                .stream()
                .map(e -> ErroValidacao.builder()
                        .mensagem(localizacaoGateway.getMensagem(e.getMensagem()))
                        .nomeParametro(e.getNomeParametro())
                        .build())
                .collect(Collectors.toList());
    }
}
