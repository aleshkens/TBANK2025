package ru.tbank.fdsspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tbank.fdsspring.entity.CurrencyEntity;


import java.util.UUID;

public interface CurrencyRepository extends JpaRepository<CurrencyEntity, UUID> {

}
