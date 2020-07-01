package com.batch.core.erro.codigo;

public final class CodigoErroNegocio {

    public static final CodigoErro NAO_ENCONTRADO =
            new CodigoErro(5, 0, "erro.negocio-nao-encontrado");

    public static final CodigoErro DADOS_DUPLICADOS =
            new CodigoErro(5,1, "erro.dados.duplicados");

    public static final CodigoErro SINTAXE_ERRADA =
            new CodigoErro(5,2, "erro.sintaxe.errada");
}
