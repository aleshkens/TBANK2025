package ru.tbank.fdsspring.dto.cbr;

import lombok.Data;

import java.util.Map;

@Data
public class CbrResponse {
    private Map<String, CbrCurrencyInfo> Valute;
}
