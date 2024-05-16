package com.rgr.webtransferback.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.hasSize;
import org.hamcrest.collection.IsEmptyCollection;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.mockito.ArgumentMatchers.anyInt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.rgr.webtransferback.models.Taxes;
import com.rgr.webtransferback.service.ITransferService;

public class WebTransferControllerTest  {
    
    @Mock
    private ITransferService service;

    @InjectMocks
    private TransferController sutController;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }
    
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 65, 90, Integer.MAX_VALUE})
    public void calculateTax_ShouldReturnBigDecimal(int daysPeriod) {
        when(service.calculateTax(anyInt())).thenReturn(BigDecimal.ONE);
        var result = this.sutController.calculateTax(1);        
        assertNotNull(result);
        assertThat(result, instanceOf(BigDecimal.class));
    }

    @Test
    public void getTaxes_ShouldReturnListOfTaxes() {

        Taxes tax = mock(Taxes.class);
        List<Taxes> list = Arrays.asList(tax, tax, tax);
        when(service.getTaxes()).thenReturn(list);

        List<Taxes> result = this.sutController.getTaxes();

        assertNotNull(result);
        assertThat(result, not(IsEmptyCollection.empty()));        
        assertThat(result, hasSize(3));
        assertThat(result, is(list));
    }

    
}
