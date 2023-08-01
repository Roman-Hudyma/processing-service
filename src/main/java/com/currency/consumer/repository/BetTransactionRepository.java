package com.currency.consumer.repository;

import com.currency.consumer.Entity.BetTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BetTransactionRepository extends JpaRepository<BetTransaction, Long> {
}
