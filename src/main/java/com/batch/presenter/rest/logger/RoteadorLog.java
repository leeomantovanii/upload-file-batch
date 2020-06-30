package com.batch.presenter.rest.logger;

import com.batch.core.erro.AplicacaoException;

public interface RoteadorLog {
    void rotearLog(AplicacaoException erro);
}
