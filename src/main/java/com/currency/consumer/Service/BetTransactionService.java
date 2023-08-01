package com.currency.consumer.Service;

import com.currency.consumer.Entity.BetTransaction;
import com.currency.consumer.repository.BetTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BetTransactionService {

    private final BetTransactionRepository betTransactionRepository;

    public void saveBetTransaction(BetTransaction betTransaction) {
        betTransactionRepository.save(betTransaction);
    }
}
