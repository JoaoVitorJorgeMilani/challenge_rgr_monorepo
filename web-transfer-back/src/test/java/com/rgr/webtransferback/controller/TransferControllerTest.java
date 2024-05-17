package com.rgr.webtransferback.controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.ValidationException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.rgr.webtransferback.exceptions.NoTaxFoundException;
import com.rgr.webtransferback.service.ITransferService;

public class TransferControllerTest  {
    
    @Mock
    private ITransferService service;

    @InjectMocks
    private TransferController sutController;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }
    
    // @ParameterizedTest
    // @ValueSource(ints = {1, 3, 15})
    // public void calculateTax_ShouldReturnBigDecimal(LocalDate transferDate, BigDecimal amount) {
    //     when(service.calculateTax(anyInt())).thenReturn(BigDecimal.ONE);
    //     var result = this.sutController.calculateTax(1);        
    //     assertNotNull(result);
    //     assertThat(result, instanceOf(BigDecimal.class));
    // }

    @Test
    public void calculateTax_ShouldReturnBigDecimal() {
       
        when(service.calculateTax(any(), any())).thenReturn(BigDecimal.ONE);

        var result = this.sutController.calculateTax(LocalDate.now(), BigDecimal.ONE);
        
        assertNotNull(result);
        assertThat(result, instanceOf(BigDecimal.class));
        assertThat(result, is(BigDecimal.ONE));
    }

    @Test
    public void calculateTax_ShouldThrowNoTaxFoundException() {   
           
        when(service.calculateTax(any(), any())).thenThrow(new NoTaxFoundException("asdf"));        
        
        RuntimeException exception = assertThrows(RuntimeException.class, () -> this.sutController.calculateTax(LocalDate.now(), BigDecimal.ONE));
        
        assertThat(exception, instanceOf(NoTaxFoundException.class));
        assertThat(exception.getMessage(), is("asdf"));
    }

    @Test
    public void calculateTax_ShouldThrowValidationException() {      
        when(service.calculateTax(any(), any())).thenThrow(new ValidationException("asdf"));        
        
        RuntimeException exception = assertThrows(RuntimeException.class, () -> this.sutController.calculateTax(LocalDate.now(), BigDecimal.ONE));
        
        assertThat(exception, instanceOf(ValidationException.class));
        assertThat(exception.getMessage(), is("asdf"));
    }


    
}
