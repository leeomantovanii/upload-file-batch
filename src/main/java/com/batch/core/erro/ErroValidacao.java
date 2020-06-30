package com.batch.core.erro;

import com.batch.core.erro.codigo.CodigoErro;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ErroValidacao {

    private String mensagem;

    private String nomeParametro;


}
