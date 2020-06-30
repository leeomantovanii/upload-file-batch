package com.batch.core.erro;

import com.batch.core.erro.codigo.CodigoErro;
import com.batch.core.erro.codigo.CodigoErroInfraestrutura;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class AplicacaoException extends RuntimeException {

    private CodigoErro codigoErro;
    
    private List<ErroValidacao> errosValidacao;

    public AplicacaoException(CodigoErro codigoErro, List<ErroValidacao> errosValidacao, String message, Throwable cause){
        super(message, cause);
        this.codigoErro = codigoErro;
        this.errosValidacao = errosValidacao;
    }

    public static AplicacaoExceptionBuilder builder(){  return new AplicacaoExceptionBuilder(); }

    public static class AplicacaoExceptionBuilder {
        private CodigoErro codigoErro = CodigoErroInfraestrutura.DESCONHECIDO;
        private List<ErroValidacao> errosValidacao = Collections.emptyList();
        private String message;
        private Throwable cause;

        public AplicacaoExceptionBuilder codigoErro(CodigoErro codigoErro){
            this.codigoErro = codigoErro;
            return this;
        }

        public AplicacaoExceptionBuilder errosValidacao(List<ErroValidacao> errosValidacao){
            this.errosValidacao = errosValidacao;
            return this;
        }

        public AplicacaoExceptionBuilder message(String message){
            this.message = message;
            return this;
        }

        public AplicacaoExceptionBuilder cause(Throwable cause){
            this.cause = cause;
            return this;
        }

        public AplicacaoException build(){
            if(message == null){
                message = codigoErro.getMensagemGenerica();
            }
            return new AplicacaoException(codigoErro, errosValidacao, message, cause);
        }
    }

}
