package ru.tbank.fdsspring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "currency")
public class CurrencyEntity {

    @Id
    private UUID id;

    private String name;

    @Column(name = "base_currency")
    private String baseCurrency;

    @Column(name = "price_change_range")
    private String priceChangeRange;

    private String description;

    public CurrencyEntity() {}

}

