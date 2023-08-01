package com.currency.cosumer.listener;

import com.currency.consumer.Service.BetTransactionService;
import com.currency.consumer.api.CurrencyRateApi;
import com.currency.consumer.api.dto.CurrencyConversationRateDto;
import com.currency.consumer.listener.WalletTransactionConsumer;
import com.currency.consumer.model.WalletTransactionDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WalletTransactionConsumerTest {

    @Mock
    private CurrencyRateApi currencyRateApi;

    @Mock
    private BetTransactionService betTransactionService;

    @InjectMocks
    private WalletTransactionConsumer walletTransactionConsumer;

    @Test
    public void testListenToWalletTransactions() {
        // Mocked input data
        String key = "12345";
        WalletTransactionDto walletTransaction = new WalletTransactionDto();
        walletTransaction.setType("BET");
        walletTransaction.setPlayerId("123424120");
        walletTransaction.setGameId("european_roulette");
        walletTransaction.setTransactionId("7d7a0b82-0c47-11ee-be56-0242ac120002");
        walletTransaction.setTimestamp(Instant.parse("2023-06-16T11:07:36.639Z"));
        walletTransaction.setAmount(10.0);
        walletTransaction.setCurrency("USD");
        walletTransaction.setBaseCurrency("EUR");

        CurrencyConversationRateDto conversationRate = new CurrencyConversationRateDto();
        conversationRate.setValue(0.913821);

        when(currencyRateApi.getCurrencyRate(eq("USD"), eq("EUR"), any(Instant.class)))
                .thenReturn(conversationRate);

        walletTransactionConsumer.listenToWalletTransactions(key, walletTransaction);

        verify(currencyRateApi, times(1))
                .getCurrencyRate(eq("USD"), eq("EUR"), eq(walletTransaction.getTimestamp()));

        verify(betTransactionService, times(1)).saveBetTransaction(argThat(betTransaction ->
                betTransaction.getType().equals("BET") &&
                        betTransaction.getPlayerId().equals("123424120") &&
                        betTransaction.getGameId().equals("european_roulette") &&
                        betTransaction.getTransactionId().equals("7d7a0b82-0c47-11ee-be56-0242ac120002") &&
                        betTransaction.getTimestamp().equals(walletTransaction.getTimestamp()) &&
                        betTransaction.getAmount() == 10.0 &&
                        betTransaction.getCurrency().equals("USD") &&
                        betTransaction.getBaseCurrency().equals("EUR") &&
                        betTransaction.getBaseCurrencyAmount() == 0.913821));
    }
}