package com.batch.core;

import com.batch.core.gateway.LogGateway;
import com.batch.core.models.DadosLog;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * UseCase responsável por possuir a regra de negocio da inserção do log em batch
 *
 * @author Leonardo
 */
@Component
@RequiredArgsConstructor
public class InserirLogBatchUseCase {

    private final LogGateway gateway;

    public void lerArquivo(Reader reader) {
        try {
            CSVParser parser = new CSVParserBuilder()
                    .withSeparator('|')
                    .build();

            CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withCSVParser(parser)
                    .build();

            String[] record;
            List<DadosLog> listDadosLog = new ArrayList<>();
            while ((record = csvReader.readNext()) != null) {
                listDadosLog.add(montaObjetoDadosLog(record));
                if (listDadosLog.size() == 200 ){
                    gateway.inserirLogBatch(listDadosLog);
                    listDadosLog.clear();
                }
            }
            gateway.inserirLogBatch(listDadosLog);
            csvReader.close();
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private DadosLog montaObjetoDadosLog(String[] dados) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

        return DadosLog.builder()
                .data(LocalDateTime.parse(dados[0], formatter))
                .ip(dados[1])
                .request(dados[2])
                .status(Integer.parseInt(dados[3]))
                .userAgent(dados[4])
                .build();
    }
}
