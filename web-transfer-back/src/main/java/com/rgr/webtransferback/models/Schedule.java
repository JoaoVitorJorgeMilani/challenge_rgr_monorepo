package com.rgr.webtransferback.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;

import com.rgr.webtransferback.repository.settings.BaseEntityAuditable;

@Entity(name = "transfer_schedules")
public class Schedule extends BaseEntityAuditable {
    
    private String source;
    private String destination;
    private BigDecimal amount;
    private BigDecimal tax;
    private LocalDate transferDate;
    private boolean deleted = false;
    private LocalDateTime deleted_at;

    public static Schedule of(ScheduleDto scheduleDto) {
        var schedule = new Schedule();
        schedule.setSource(scheduleDto.getSource());
        schedule.setDestination(scheduleDto.getDestination());
        schedule.setAmount(scheduleDto.getAmount());
        schedule.setTransferDate(scheduleDto.getTransferDate());
        schedule.setTax(scheduleDto.getTax());

        return schedule;
    }

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

    public boolean isDeleted() {
        return deleted;
    }
    
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    public LocalDateTime getDeleted_at() {
        return deleted_at;
    }
    public void setDeleted_at(LocalDateTime deleted_at) {
        this.deleted_at = deleted_at;
    }

    
}
