package com.bootcamp.bc.bc_yahoo_finance.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.bootcamp.bc.bc_yahoo_finance.entity.QuoteEntity;

@Repository
public interface QuoteRepository extends JpaRepository<QuoteEntity, Long> {

        @Query(value = "SELECT MAX(regular_market_time) FROM tstock_quote_yahoo",
                        nativeQuery = true)
        Long findMaxUnixTime();

        @Query(value = "SELECT MAX(regular_market_time) FROM tstock_quote_yahoo WHERE symbol = :symbol",
                        nativeQuery = true)
        Long findMaxUnixTime(@Param("symbol") String symbol);

        @Query(value = "SELECT * FROM tstock_quote_yahoo WHERE to_char(to_timestamp(regular_market_time), 'YYYY-MM-DD') = :date AND symbol = :symbol ORDER BY regular_market_time",
                        nativeQuery = true)
        List<QuoteEntity> findAllByDate(@Param("date") String date,
                        @Param("symbol") String symbol); // YYYY-MM-DD

}
