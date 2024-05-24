package com.rgr.webtransferback.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.data.domain.Page;

import com.rgr.webtransferback.models.ScheduleDto;
import com.rgr.webtransferback.models.Tax;

public interface ITransferService {
    
    public CompletableFuture<BigDecimal> calculateTax(LocalDate transferDate,  BigDecimal amount);
    public CompletableFuture<BigDecimal> calculateTax(ScheduleDto daysPeriod);
    public ScheduleDto saveSchedule(ScheduleDto schedule);
    public Page<ScheduleDto> listSchedule(int page, int size);
    public void deleteSchedule(String encryptedId);
    public ScheduleDto getSchedule(String encryptedId);
    public List<Tax> listTaxes();
    
}
