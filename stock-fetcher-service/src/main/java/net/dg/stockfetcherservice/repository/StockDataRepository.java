package net.dg.stockfetcherservice.repository;

import java.util.Optional;
import net.dg.stockfetcherservice.model.entity.StockData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockDataRepository extends JpaRepository<StockData, Long> {

  Optional<StockData> findBySymbol(String symbol);
}
