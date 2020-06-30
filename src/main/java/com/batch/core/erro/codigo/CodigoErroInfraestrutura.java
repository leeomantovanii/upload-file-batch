package com.batch.core.erro.codigo;

public final class CodigoErroInfraestrutura {

    private CodigoErroInfraestrutura(){

    }

    public static final CodigoErro DESCONHECIDO =
            new CodigoErro(6, 66666, "erro.infra.desconhecido");
}
