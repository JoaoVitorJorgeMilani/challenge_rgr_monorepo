package com.rgr.webtransferback.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import javax.validation.ValidationException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.rgr.webtransferback.config.AsyncExecutor;
import com.rgr.webtransferback.repository.ITaxesRepository;


public class TransferServiceTest {
 
    @Mock
    private ITaxesRepository repository;

    // Spying it for call real method purpous
    @Spy
    private AsyncExecutor executor;

    @InjectMocks
    private TransferService sutTransferService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);        
    }

    @ParameterizedTest
    @MethodSource("provideParametersValidDays")
    public void calculateTax_ShouldReturnBigDecimal(LocalDate transferDate) throws InterruptedException, ExecutionException {
        var daysPeriod = (int) DAYS.between(LocalDate.now(), transferDate);
        when(repository.getTax(daysPeriod)).thenReturn(BigDecimal.ONE);
    
        BigDecimal result = sutTransferService.calculateTax(transferDate, BigDecimal.ONE).orTimeout(5000, TimeUnit.MILLISECONDS).join();
      
        assertNotNull(result);
        assertThat(result, instanceOf(BigDecimal.class));
    }

    private static Stream<Arguments> provideParametersValidDays() {
        return Stream.of(
                Arguments.of(LocalDate.now()),
                Arguments.of(LocalDate.now().plusDays(64)),
                Arguments.of(LocalDate.now().plusYears(666))
        );
    }

    @ParameterizedTest
    @MethodSource("provideParametersDaysBeforeCurrentDate")
    public void calculateTax_WithNullTax_ShouldReturnValidationException(LocalDate transferDate) {
        var daysPeriod = (int) DAYS.between(LocalDate.now(), transferDate);
        when(repository.getTax(daysPeriod)).thenReturn(null);
        
        RuntimeException exception = assertThrows(RuntimeException.class, () -> sutTransferService.calculateTax(transferDate, BigDecimal.ONE));

        assertThat(exception, instanceOf(ValidationException.class));
    }

    @ParameterizedTest
    @MethodSource("provideParametersDaysBeforeCurrentDate")
    public void calculateTax_WithNegativeTax_ShouldReturnValidationException(LocalDate transferDate) {
        var daysPeriod = (int) DAYS.between(LocalDate.now(), transferDate);
        when(repository.getTax(daysPeriod)).thenReturn(BigDecimal.valueOf(-1));
        
        RuntimeException exception = assertThrows(RuntimeException.class, () -> sutTransferService.calculateTax(transferDate, BigDecimal.ONE));

        assertThat(exception, instanceOf(ValidationException.class));
    }

    private static Stream<Arguments> provideParametersDaysBeforeCurrentDate() {
        return Stream.of(
                Arguments.of(LocalDate.now().minusDays(1)),
                Arguments.of(LocalDate.now().minusWeeks(1)),
                Arguments.of(LocalDate.now().minusMonths(1)),
                Arguments.of(LocalDate.now().minusYears(1)),
                Arguments.of(LocalDate.now().minusYears(666))
        );
    }


}