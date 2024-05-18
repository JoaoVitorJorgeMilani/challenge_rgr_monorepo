package com.rgr.webtransferback.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.domain.Page;

import com.rgr.webtransferback.models.ScheduleDto;

public interface ITransferService {
    
    public BigDecimal calculateTax(LocalDate transferDate,  BigDecimal amount);
    public BigDecimal calculateTax(ScheduleDto daysPeriod);
    public ScheduleDto saveSchedule(ScheduleDto schedule);
    public Page<ScheduleDto> listSchedule(int page, int size);
    public void deleteSchedule(String encryptedId);
    public ScheduleDto getSchedule(String encryptedId);
    
}
