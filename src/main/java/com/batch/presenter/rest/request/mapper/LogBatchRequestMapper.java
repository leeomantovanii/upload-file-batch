package com.batch.presenter.rest.request.mapper;

import org.mapstruct.Mapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Mapper responsável por converter objeto do tipo request em core e objeto do tipo core em response
 *
 * @author Leonardo
 */
@Mapper(componentModel = "spring")
public interface LogBatchRequestMapper {

    default Reader requestToCore(MultipartFile fileLog) throws IOException {
        return new InputStreamReader((fileLog.getInputStream()));
    }
}
