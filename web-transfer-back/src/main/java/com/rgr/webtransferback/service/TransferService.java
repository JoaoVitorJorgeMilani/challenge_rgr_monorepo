package com.rgr.webtransferback.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rgr.webtransferback.exceptions.NoTaxFoundException;
import com.rgr.webtransferback.models.Schedule;
import com.rgr.webtransferback.models.ScheduleDto;
import com.rgr.webtransferback.repository.IScheduleRepository;
import com.rgr.webtransferback.repository.ITransferRepository;
import com.rgr.webtransferback.util.encryption.IEncryptor;

@Service
public class TransferService implements ITransferService {

    private final ITransferRepository taxRepository;
    private final IScheduleRepository scheduleRepository;
    private final IEncryptor encryptor;

    @Autowired
    public TransferService(ITransferRepository repository, IScheduleRepository scheduleRepository, IEncryptor encryptor) {
        this.taxRepository = repository;
        this.scheduleRepository = scheduleRepository;
        this.encryptor = encryptor;
    }

    @Override
    public BigDecimal calculateTax(LocalDate transferDate,  BigDecimal amount) {
        if(transferDate.isBefore(LocalDate.now()))
            throw new ValidationException("Date cannot be in the past");
        
        int daysPeriod = (int) DAYS.between(LocalDate.now(), transferDate);
        var taxPercent = taxRepository.getTax(daysPeriod);

        if(taxPercent == null || taxPercent.compareTo(BigDecimal.ZERO) == -1)
            throw new NoTaxFoundException("Tax not found");
        
        if(taxPercent.compareTo(BigDecimal.ZERO) == 0)
            return BigDecimal.ZERO;
        
        return amount.divide(new BigDecimal("100")).multiply(taxPercent).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal calculateTax(ScheduleDto schedule) throws ValidationException {
        var tax = calculateTax(schedule.getTransferDate(), schedule.getAmount());
        return tax;
    }

    @Override
    public ScheduleDto saveSchedule(ScheduleDto schedule) {
        Schedule savedSchedule = this.scheduleRepository.save(Schedule.of(schedule));
        return ScheduleDto.of(savedSchedule, encryptor);
    }
    
}
