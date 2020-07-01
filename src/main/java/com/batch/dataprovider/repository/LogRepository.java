package com.batch.dataprovider.repository;

import com.batch.dataprovider.entity.LogTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface LogRepository extends JpaRepository<LogTable, String> {

    Optional<List<LogTable>> findByIp(String ip);

    Optional<List<LogTable>> findByIpAndDataBetween(String ip, LocalDateTime dtInicio, LocalDateTime dtFim);

    Optional<List<LogTable>> findByDataBetween(LocalDateTime dtInicio, LocalDateTime dtFim);
}
