package com.rgr.webtransferback.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.rgr.webtransferback.models.ScheduleDto;

public interface ITransferService {
    
    public BigDecimal calculateTax(LocalDate transferDate,  BigDecimal amount);
    public BigDecimal calculateTax(ScheduleDto daysPeriod);
    public ScheduleDto saveSchedule(ScheduleDto schedule);

    
}
