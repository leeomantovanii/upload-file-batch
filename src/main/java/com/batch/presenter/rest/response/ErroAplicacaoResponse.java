package com.batch.presenter.rest.response;

import lombok.Data;

import java.util.List;

@Data
public class ErroAplicacaoResponse {
    int codigo;
    String mensagem;
    List<ErroValidacaoResponse> atributos;
}
