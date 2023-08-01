package com.currency.consumer.Entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;

@Data
@Entity
@Builder
public class BetTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String playerId;
    private String gameId;
    private String transactionId;
    private Instant timestamp;
    private double amount;
    private String currency;
    private String baseCurrency;
    private double baseCurrencyAmount;
}
