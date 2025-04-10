package ru.tbank.fdsspring.service;

import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import ru.tbank.fdsspring.entity.CurrencyEntity;
import ru.tbank.fdsspring.repository.CurrencyRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CurrencyService {

    private final CurrencyRepository repository;

    public CurrencyService(CurrencyRepository repository) {
        this.repository = repository;
    }

    public List<CurrencyEntity> getAll() {
        return repository.findAll();
    }

    public Optional<CurrencyEntity> getById(UUID id) {
        return repository.findById(id);
    }

    public CurrencyEntity create(CurrencyEntity entity) {
        entity.setId(UUID.randomUUID());
        return repository.save(entity);
    }

    public CurrencyEntity update(UUID id, CurrencyEntity details) {
        return repository.findById(id)
                .map(currency -> {
                    currency.setName(details.getName());
                    currency.setBaseCurrency(details.getBaseCurrency());
                    currency.setPriceChangeRange(details.getPriceChangeRange());
                    currency.setDescription(details.getDescription());
                    return repository.save(currency);
                })
                .orElseThrow(() -> new EntityNotFoundException("Currency not found"));
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}

