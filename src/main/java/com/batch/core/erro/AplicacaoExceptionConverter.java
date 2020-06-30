package com.batch.core.erro;

/**
 * Abstração de um conversor de {@link Throwable} para {@link AplicacaoException}
 * Usado por {@link ResolverExceptionUseCase} para fazer a conversao
 *
 * @param <E> Tipo do {@link Throwable} a ser convertido
 */
public abstract class AplicacaoExceptionConverter<E extends Throwable> {

    private Class<E> classeDestino;

    protected  AplicacaoExceptionConverter(Class<E> classeDestino){
        this.classeDestino = classeDestino;
    }

    public abstract AplicacaoException.AplicacaoExceptionBuilder converter (AplicacaoException.AplicacaoExceptionBuilder builder, E exception);

    public Class<E> getClasseDestino() { return classeDestino; }
}
