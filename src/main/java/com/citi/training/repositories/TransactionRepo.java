package com.citi.training.repositories;

import com.citi.training.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

    ArrayList<Transaction> findByStrategyId(Integer strategyId); //returns list of transactions for specific strategy
    ArrayList<Transaction> findByStrategyIdAndStockPosition(Integer strategyId, String stockPosition); //returns list of transactions for specific strategy

    @Query("select sum(strat.volume) from Transaction trans, Strategy strat where trans.strategyId = strat.id and strat.stockName = ?1 and trans.stockPosition = 'BUY' and trans.isComplete = 'n'")
    Integer findCurrentVolumeOfStock(String stockName);

    @Query(value = "SELECT DISTINCT(trans.stock_name) from transaction trans", nativeQuery = true)
    List<String> getDistinctStockName();

    @Query(value = "select * from witi.transaction where strategy_id = ?1 order by id DESC limit 1", nativeQuery = true)
    Transaction getLatestTransactionById(Integer id);

    @Query(value = "select * from witi.transaction where strategy_id = ?1 and stock_position = 'SELL' order by id DESC limit 1", nativeQuery = true)
    Transaction getLatestSellTransactionById(Integer id);

    @Query(value = "select * from witi.transaction where strategy_id = ?1 and stock_position = 'SELL' and transaction_time <= ?2 order by id DESC limit 1", nativeQuery = true)
    Transaction getLatestSellTransactionByIdAndTime(Integer id, String time);


}