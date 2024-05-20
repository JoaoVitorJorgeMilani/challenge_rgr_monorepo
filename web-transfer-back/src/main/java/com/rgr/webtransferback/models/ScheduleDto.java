package com.rgr.webtransferback.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.rgr.webtransferback.util.encryption.IEncryptor;

public class ScheduleDto {
    private String encryptedId;
    private String source;
    private String destination;
    private BigDecimal amount;
    private BigDecimal tax;
    private LocalDate transferDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ScheduleDto of(Schedule schedule, IEncryptor encryptor) {
        var scheduleDto = new ScheduleDto();
        scheduleDto.setEncryptedId(encryptor.encrypt(schedule.getId().toString()));
        scheduleDto.setSource(schedule.getSource());
        scheduleDto.setDestination(schedule.getDestination());
        scheduleDto.setAmount(schedule.getAmount());
        scheduleDto.setTax(schedule.getTax());
        scheduleDto.setTransferDate(schedule.getTransferDate());

        scheduleDto.setCreatedAt(schedule.getCreatedAt());
        scheduleDto.setUpdatedAt(schedule.getUpdatedAt());
        return scheduleDto;
    }

    private void setEncryptedId(String encryptedId) {
        this.encryptedId = encryptedId;
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

    public void setTransferDate(LocalDate date) {
        this.transferDate = date;
    }

    public String getEncryptedId() {
        return encryptedId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }   

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }   


}
