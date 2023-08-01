package com.currency.consumer.listener;

import com.currency.consumer.Entity.BetTransaction;
import com.currency.consumer.Service.BetTransactionService;
import com.currency.consumer.api.CurrencyRateApi;
import com.currency.consumer.api.dto.CurrencyConversationRateDto;
import com.currency.consumer.model.WalletTransactionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class WalletTransactionConsumer {

    private final CurrencyRateApi currencyRateApi;

    private final BetTransactionService betTransactionService;

    @KafkaListener(topics = "${currency-transaction-topic}")
    public void listenToWalletTransactions(String key, WalletTransactionDto walletTransaction) {
        CurrencyConversationRateDto conversationRate = currencyRateApi.getCurrencyRate(walletTransaction.getCurrency(),
                walletTransaction.getBaseCurrency(), walletTransaction.getTimestamp());

        betTransactionService.saveBetTransaction(BetTransaction.builder()
                .type(walletTransaction.getType())
                .playerId(walletTransaction.getPlayerId())
                .gameId(walletTransaction.getGameId())
                .transactionId(walletTransaction.getTransactionId())
                .timestamp(walletTransaction.getTimestamp())
                .amount(walletTransaction.getAmount())
                .currency(walletTransaction.getCurrency())
                .baseCurrency(walletTransaction.getBaseCurrency())
                .baseCurrencyAmount(conversationRate.getValue())
                .build());
    }
}
