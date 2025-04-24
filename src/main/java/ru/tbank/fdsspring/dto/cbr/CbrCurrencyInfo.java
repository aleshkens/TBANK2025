package ru.tbank.fdsspring.dto.cbr;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CbrCurrencyInfo {
    private String ID;
    private String CharCode;
    private String Name;
    private BigDecimal Value;
    private BigDecimal Previous;
}
