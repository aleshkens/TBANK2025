package ru.tbank.fdsspring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.tbank.fdsspring.dto.cbr.CbrCurrencyInfo;
import ru.tbank.fdsspring.dto.cbr.CbrResponse;
import ru.tbank.fdsspring.entity.CurrencyEntity;
import ru.tbank.fdsspring.repository.CurrencyRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CurrencyMonitorJob {

    private final CurrencyRepository currencyRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    @Scheduled(cron = "0 0 * * * *")
    public void checkCurrencyChanges() {
        try {
            ResponseEntity<CbrResponse> response = restTemplate.getForEntity(
                    "https://www.cbr-xml-daily.ru/daily_json.js",
                    CbrResponse.class
            );

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                Map<String, CbrCurrencyInfo> valuteMap = response.getBody().getValute();
                List<CurrencyEntity> dbCurrencies = currencyRepository.findAll();

                for (CurrencyEntity dbCurrency : dbCurrencies) {
                    CbrCurrencyInfo info = valuteMap.get(dbCurrency.getBaseCurrency());

                    if (info != null) {
                        BigDecimal value = info.getValue();
                        BigDecimal previous = info.getPrevious();
                        BigDecimal rangePercent = parsePercent(dbCurrency.getPriceChangeRange());
                        BigDecimal threshold = previous.multiply(rangePercent.abs()).divide(BigDecimal.valueOf(100));

                        boolean changed = rangePercent.compareTo(BigDecimal.ZERO) > 0
                                ? value.subtract(previous).compareTo(threshold) >= 0
                                : previous.subtract(value).compareTo(threshold) >= 0;

                        if (changed) {
                            System.out.println("Уведомление: " + dbCurrency.getDescription());
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private BigDecimal parsePercent(String percent) {
        return new BigDecimal(percent.replace("%", "").replace(",", "."));
    }
}
