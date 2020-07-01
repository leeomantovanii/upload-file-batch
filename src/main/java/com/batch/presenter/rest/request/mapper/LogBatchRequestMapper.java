package com.batch.presenter.rest.request.mapper;

import com.batch.core.models.DadosLog;
import com.batch.presenter.rest.request.LogRequest;
import com.batch.presenter.rest.response.LogResponse;
import org.mapstruct.Mapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

/**
 * Mapper responsável por converter objeto do tipo request em core e objeto do tipo core em response
 *
 * @author Leonardo
 */
@Mapper(componentModel = "spring")
public interface LogBatchRequestMapper {

    /**
     * Método responsável por converter MultipartFile em Reader
     *
     * @param fileLog
     * @return
     * @throws IOException
     */
    default Reader requestBatchToCore(MultipartFile fileLog) throws IOException {
        return new InputStreamReader((fileLog.getInputStream()));
    }

    /**
     * Método responsável por converter dados de requeste em core
     *
     * @param request
     * @return
     */
    DadosLog requestToCore(LogRequest request);

    /**
     * Método responsável por converter dados de core em response
     * @param core
     * @return
     */
    LogResponse coreToResponse(DadosLog core);

    /**
     * Método responsável por converter uma list de core em list de response
     * @param core
     * @return
     */
    List<LogResponse> listCoreToListResponse(List<DadosLog> core);

}
