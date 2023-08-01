package com.currency.consumer.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.Instant;

@Data
public class WalletTransactionDto {
    private String type;
    private String playerId;
    private String gameId;
    private String transactionId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Instant timestamp;

    private double amount;
    private String currency;
    private String baseCurrency;
}
