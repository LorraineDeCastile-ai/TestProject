package com.twcoding.TestProject.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.twcoding.TestProject.entity.DailyForeignExchangeRatesEntity;

@Repository
public interface DailyForeignExchangeRatesRepository extends JpaRepository<DailyForeignExchangeRatesEntity, Long> {
	List<DailyForeignExchangeRatesEntity> findByDateAndCurrencyTypeAndExchangeCurrencyType(String date, String currencyType, String exchangeCurrencyType);
}
