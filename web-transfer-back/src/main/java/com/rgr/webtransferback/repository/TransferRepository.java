package com.rgr.webtransferback.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rgr.webtransferback.models.Taxes;

@Repository
public interface TransferRepository extends JpaRepository<Taxes, Integer>  {
   
    // TODO tax calculation in this case should be in a stored procedure, implemented in Query for now.
    @Query(value = "SELECT t.tax FROM transfer_taxes t WHERE t.days_period >= :daysPeriod ORDER BY t.days_period LIMIT 1", nativeQuery = true)
    BigDecimal getTax(@Param("daysPeriod") int daysPeriod);

    @Query(value = "SELECT * FROM transfer_taxes", nativeQuery = true)
    List<Taxes> getTaxes();
   
}
