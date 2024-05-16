package com.rgr.webtransferback.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;

import com.rgr.webtransferback.repository.settings.BaseEntityAuditable;

@Entity(name = "schedules")
public class Schedule extends BaseEntityAuditable {
    
    private String source;
    private String destination;
    private BigDecimal amount;
    private BigDecimal tax;
    private LocalDate transferDate;

    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public BigDecimal getTax() {
        return tax;
    }
    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }
    public LocalDate getTransferDate() {
        return transferDate;
    }
    public void setTransferDate(LocalDate transferDate) {
        this.transferDate = transferDate;
    }

    
}
