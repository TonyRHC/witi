package com.citi.training.services;

import com.citi.training.entities.Transaction;

import java.util.ArrayList;
import java.util.List;

public interface TransactionService {
    List<Transaction> getAllTransactions();
    void addTransaction(Transaction transaction); //adds this new transaction to the table
    ArrayList<Transaction> getTransactionsByStrategyId(Integer stratId);
    Transaction getLatestSellTransactionById(Integer id);
    Transaction getLatestSellTransactionByIdAndTime(Integer id, String time);
    ArrayList<Transaction> getTransactionsByStrategyIdAndStockPosition(Integer stratId, String stockPosition);
    Integer findCurrentVolumeOfStock(String stockName);
    Iterable<String> findDistinctByStockName();
    Transaction getLatestTransactionById(Integer stratId);

}

