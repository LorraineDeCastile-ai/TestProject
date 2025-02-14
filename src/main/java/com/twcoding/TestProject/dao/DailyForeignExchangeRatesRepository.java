package com.twcoding.TestProject.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.twcoding.TestProject.entity.DailyForeignExchangeRatesEntity;

@Repository
public interface DailyForeignExchangeRatesRepository extends JpaRepository<DailyForeignExchangeRatesEntity, Long> {
	List<DailyForeignExchangeRatesEntity> findByDateAndCurrencyTypeAndExchangeCurrencyType(String date, String currencyType, String exchangeCurrencyType);
	
	@Query("SELECT dfer FROM DailyForeignExchangeRatesEntity dfer WHERE dfer.date >= :startDate and dfer.date <= :endDate and dfer.currencyType = :currencyType and dfer.exchangeCurrencyType = 'NTD' ")
	List<DailyForeignExchangeRatesEntity> findByDateAndCurrencyType(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("currencyType") String currencyType);
}
