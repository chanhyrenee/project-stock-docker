package com.bootcamp.bc.bc_yahoo_finance.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.bootcamp.bc.bc_yahoo_finance.entity.HistoryEntity;

@Repository
public interface HistoryRepository extends JpaRepository<HistoryEntity, Long> {

  @Query(
      value = "SELECT * FROM tstock_history_yahoo WHERE symbol = :symbol AND timestamp BETWEEN :start AND :end ORDER BY timestamp DESC",
      nativeQuery = true)
  List<HistoryEntity> findByRange(String symbol, Long start, Long end);

  List<HistoryEntity> findBySymbol(String symbol);

}
