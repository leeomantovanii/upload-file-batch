package com.batch.core.erro;

import com.batch.core.anotacao.UseCase;
import com.batch.core.erro.codigo.CodigoErroInfraestrutura;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Resolve uma exception de qualquer classe para uma {@link AplicacaoException}
 * Caso um {@link AplicacaoExceptionConverter} não exista, uma {@link AplicacaoException} do tipo erro desconhecido é criada
 * <p>
 * Caso o {@link Throwable} recebido seja uma sub-classe de alguma exception convertida por um conversor de exceptions,
 * ele será usado. Por exemplo:
 *
 * <pre>
 *     class CustomException extends RuntimeException {}
 *     class RunTimeConverter extends AplicacaoExceptionConverter&lt;RuntimeExpection&gt; {...} }
 * </pre>
 * <p>
 * Neste caso, o {@code RuntimeConverter} será usada para converter a exception, pois {@code CustomException} é uma sub-classe de {@code RunTimeException}
 */
@UseCase
@SuppressWarnings({"rawtypes", "unchecked"})
public class ResolverExceptionUseCase {

    private final Map<Class<?>, AplicacaoExceptionConverter> converterMap;
    private final DesconhecidoConverter desconhecidoConverter = new DesconhecidoConverter();

    public ResolverExceptionUseCase(List<AplicacaoExceptionConverter<?>> converters) {
        converterMap = new HashMap<>();
        converters.forEach(c -> converterMap.put(c.getClasseDestino(), c));
    }

    public AplicacaoException resolverException(Throwable ex) {
        if (ex instanceof AplicacaoException) {
            return (AplicacaoException) ex;
        }

        return Optional.ofNullable(ex)
                .map(e -> converterException(AplicacaoException.builder(), e))
                .orElseGet(() -> desconhecidoConverter.converter(AplicacaoException.builder(), null))
                .build();
    }

    private AplicacaoException.AplicacaoExceptionBuilder converterException(AplicacaoException.AplicacaoExceptionBuilder builder, Throwable e){
        return buscarConverter(e)
                .converter(builder, e);
    }

    private AplicacaoExceptionConverter buscarConverter(Throwable e){
        AplicacaoExceptionConverter converter = converterMap.get(e.getClass());

        if (converter == null){
            converter = converterMap.values().stream()
                    .filter(c -> c.getClasseDestino().isAssignableFrom(e.getClass()))
                    .findAny()
                    .orElse(desconhecidoConverter);
        }

        return converter;
    }

    private static class DesconhecidoConverter extends  AplicacaoExceptionConverter<Exception>{

        protected DesconhecidoConverter () { super(Exception.class); }

        @Override
        public AplicacaoException.AplicacaoExceptionBuilder converter (AplicacaoException.AplicacaoExceptionBuilder builder, Exception exception){
            return builder.codigoErro(CodigoErroInfraestrutura.DESCONHECIDO);
        }
    }

}
