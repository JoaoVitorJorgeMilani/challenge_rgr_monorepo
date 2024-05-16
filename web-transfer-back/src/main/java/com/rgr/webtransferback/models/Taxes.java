package com.rgr.webtransferback.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transfer_taxes")
public class Taxes {
    
    @Id
    private int days_period;
    
    private BigDecimal tax;

    public int getDays_period() {
        return days_period;
    }

    public void setDays_period(int days_period) {
        this.days_period = days_period;
    }

    public BigDecimal getTax_value() {
        return tax;
    }

    public void setTax_value(BigDecimal tax_value) {
        this.tax = tax_value;
    }



}
