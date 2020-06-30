package com.batch.presenter.rest.response;

import com.batch.core.erro.AplicacaoException;
import com.batch.core.erro.ErroValidacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ErroResponseMapper {

    @Mapping(source = "message", target = "mensagem")
    @Mapping(source = "codigoErro.codigo", target = "codigo")
    @Mapping(target = "atributos", source = "errosValidacao")
    ErroAplicacaoResponse erroAplicacao(AplicacaoException ex);


    @Mapping(target = "nome", source = "nomeParametro")
    ErroValidacaoResponse erroValidacao(ErroValidacao erroValidacao);
}
