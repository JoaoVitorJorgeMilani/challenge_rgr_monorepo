package com.rgr.webtransferback.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.rgr.webtransferback.models.ScheduleDto;
import com.rgr.webtransferback.models.Taxes;

public interface ITransferService {
    
    public BigDecimal calculateTax(LocalDate transferDate,  BigDecimal amount);
    public BigDecimal calculateTax(ScheduleDto daysPeriod);
    public List<Taxes> getTaxes();
    
}
