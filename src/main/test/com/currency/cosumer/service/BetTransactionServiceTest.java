package com.currency.cosumer.service;

import com.currency.consumer.Entity.BetTransaction;
import com.currency.consumer.Service.BetTransactionService;
import com.currency.consumer.repository.BetTransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BetTransactionServiceTest {

    @Mock
    private BetTransactionRepository betTransactionRepository;

    @InjectMocks
    private BetTransactionService betTransactionService;

    @Test
    public void testSaveBetTransaction() {
        BetTransaction betTransaction = BetTransaction.builder()
                .type("BET")
                .playerId("123424120").build();

        betTransactionService.saveBetTransaction(betTransaction);

        verify(betTransactionRepository, times(1)).save(betTransaction);
    }
}

