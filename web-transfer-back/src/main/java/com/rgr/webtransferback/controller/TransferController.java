package com.rgr.webtransferback.controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rgr.webtransferback.models.ScheduleDto;
import com.rgr.webtransferback.service.ITransferService;
import com.rgr.webtransferback.util.DevTools;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"})
public class TransferController {

    private final ITransferService service;

    @Autowired
    public TransferController(ITransferService service){
        this.service = service;
    }

    @GetMapping("/transfer/calculate")
    public BigDecimal calculateTax(
        @RequestParam("transferDate")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate transferDate, 
        @RequestParam BigDecimal amount
    ) {
        return service.calculateTax(transferDate, amount);
    }

    @PostMapping("/transfer/save")
    public ScheduleDto saveSchedule(@RequestBody ScheduleDto schedule) {
        System.out.println("DEBUGGING Controller received schedule");
        DevTools.dumpObjectOnConsole(schedule);

        return this.service.saveSchedule(schedule);        
    }
}