package com.batch.dataprovider.repository;

import com.batch.dataprovider.entity.LogId;
import com.batch.dataprovider.entity.LogTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<LogTable, LogId> {
}
