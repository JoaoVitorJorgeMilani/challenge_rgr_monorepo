package com.rgr.webtransferback.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rgr.webtransferback.exceptions.NoTaxFoundException;
import com.rgr.webtransferback.models.Schedule;
import com.rgr.webtransferback.models.ScheduleDto;
import com.rgr.webtransferback.models.Tax;
import com.rgr.webtransferback.repository.IScheduleRepository;
import com.rgr.webtransferback.repository.ITaxesRepository;
import com.rgr.webtransferback.util.encryption.IEncryptor;

@Service
public class TransferService implements ITransferService {

    private final ITaxesRepository taxesRepository;
    private final IScheduleRepository scheduleRepository;
    private final IEncryptor encryptor;

    @Autowired
    public TransferService(ITaxesRepository repository, IScheduleRepository scheduleRepository, IEncryptor encryptor) {
        this.taxesRepository = repository;
        this.scheduleRepository = scheduleRepository;
        this.encryptor = encryptor;
    }

    @Override
    public CompletableFuture<BigDecimal> calculateTax(LocalDate transferDate, BigDecimal amount) {
        if (transferDate.isBefore(LocalDate.now()))
            throw new ValidationException("Date cannot be in the past");

        int daysPeriod = (int) DAYS.between(LocalDate.now(), transferDate);
        CompletableFuture<BigDecimal> taxFuture = taxesRepository.getTax(daysPeriod);

        return taxFuture.thenApply(taxPercent -> {
            if (taxPercent == null) {
                throw new NoTaxFoundException("Tax not found");
            }
            if (taxPercent.compareTo(BigDecimal.ZERO) == 0)
                return BigDecimal.ZERO;

            return amount.divide(new BigDecimal("100")).multiply(taxPercent).setScale(2, RoundingMode.HALF_UP);
        });
    }

    @Override
    public CompletableFuture<BigDecimal> calculateTax(ScheduleDto schedule) throws ValidationException {
        return calculateTax(schedule.getTransferDate(), schedule.getAmount());
        
    }

    @Override
    public ScheduleDto saveSchedule(ScheduleDto schedule) {
        Schedule savedSchedule = this.scheduleRepository.save(Schedule.of(schedule));
        return ScheduleDto.of(savedSchedule, encryptor);
    }

    @Override
    public Page<ScheduleDto> listSchedule(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Schedule> schedules = scheduleRepository.findAllActive(pageable);

        List<ScheduleDto> dtos = schedules.getContent()
                .stream()
                .map(schedule -> ScheduleDto.of(schedule, encryptor))
                .collect(Collectors.toList());

        return new PageImpl<>(dtos, pageable, schedules.getTotalElements());

    }

    @Override
    public void deleteSchedule(String encryptedId) {
        try {
            UUID scheduleId = encryptor.decryptUuid(encryptedId);
            Schedule schedule = getSchedule(scheduleId);

            if (schedule == null)
                throw new ValidationException("Invalid id");

            if (schedule.isDeleted())
                throw new ValidationException("Already deleted");

            schedule.setDeleted(true);
            schedule.setDeleted_at(LocalDateTime.now());

            schedule = this.scheduleRepository.save(schedule);

            if (!schedule.isDeleted())
                throw new ValidationException("Failed to delete");

        } catch (ValidationException e) {
            throw new ValidationException(e.getMessage());
        }
    }

    @Override
    public ScheduleDto getSchedule(String encryptedId) {
        try {
            UUID scheduleId = encryptor.decryptUuid(encryptedId);

            Schedule schedule = getSchedule(scheduleId);

            if (schedule == null)
                throw new ValidationException("Schedule not found");

            return ScheduleDto.of(schedule, encryptor);
        } catch (ValidationException e) {
            throw new ValidationException(e.getMessage());
        }
    }

    private Schedule getSchedule(UUID id) {
        return scheduleRepository.findById(id).orElse(null);
    }

    @Override
    public List<Tax> listTaxes() {
        return taxesRepository.findAll();
    }

}
