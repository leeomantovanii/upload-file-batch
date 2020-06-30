package com.batch.core.anotacao;

import java.lang.annotation.*;

/**
 * Anotação para especificar um UseCase. Útil para frameworks que utilizam da técnica component scan
 *
 * @author Leonardo
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UseCase {
}
