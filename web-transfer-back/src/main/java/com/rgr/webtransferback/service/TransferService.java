package com.rgr.webtransferback.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rgr.webtransferback.exceptions.NoTaxFoundException;
import com.rgr.webtransferback.models.ScheduleDto;
import com.rgr.webtransferback.models.Taxes;
import com.rgr.webtransferback.repository.TransferRepository;

@Service
public class TransferService implements ITransferService {

    private final TransferRepository repository;

    @Autowired
    public TransferService(TransferRepository repository) {
        this.repository = repository;

    }

    @Override
    public BigDecimal calculateTax(LocalDate transferDate,  BigDecimal amount) {
        if(transferDate.isBefore(LocalDate.now()))
            throw new ValidationException("Date cannot be in the past");
        
        int daysPeriod = (int) DAYS.between(LocalDate.now(), transferDate);
        var taxPercent = repository.getTax(daysPeriod);

        System.out.println("Tax percent: " + taxPercent);
        System.out.println("Amount: " + amount);

        if(taxPercent == null || taxPercent.compareTo(BigDecimal.ZERO) == -1)
            throw new NoTaxFoundException("Tax not found");
        
        if(taxPercent.compareTo(BigDecimal.ZERO) == 0)
            return BigDecimal.ZERO;
        
        return amount.divide(new BigDecimal("100")).multiply(taxPercent).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal calculateTax(ScheduleDto schedule) throws ValidationException {
        if(schedule.getDate().isBefore(LocalDateTime.now()))
            throw new ValidationException("Date cannot be in the past");

        int daysPeriod = (int) DAYS.between(LocalDateTime.now(), schedule.getDate());
        
        var tax = repository.getTax(daysPeriod);

        if(tax == null)
            throw new NoTaxFoundException("Tax not found");

        return tax;
    }

    @Override
    public List<Taxes> getTaxes() {
        return repository.getTaxes();
    }
    
}
