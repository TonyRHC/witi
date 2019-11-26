package com.citi.training.services;

import com.citi.training.entities.Transaction;
import com.citi.training.repositories.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepo dao;

    @Override
    public List<Transaction> getAllTransactions() {
        return dao.findAll();
    }



    @Override
    public void addTransaction(Transaction transaction) {
        dao.save(transaction);
    }

    @Override
    public ArrayList<Transaction> getTransactionsByStrategyId(Integer stratId) {
        return dao.findByStrategyId(stratId);
    }

    @Override
    public Transaction getLatestSellTransactionById(Integer id) {
        return dao.getLatestSellTransactionById(id);
    }

    @Override
    public Transaction getLatestSellTransactionByIdAndTime(Integer id, String time) {
        return dao.getLatestSellTransactionByIdAndTime(id, time);
    }

    @Override
    public ArrayList<Transaction> getTransactionsByStrategyIdAndStockPosition(Integer stratId, String stockPosition) {
        return dao.findByStrategyIdAndStockPosition(stratId, stockPosition);
    }


    @Override
    public Integer findCurrentVolumeOfStock(String stockName) {
        if (dao.findCurrentVolumeOfStock(stockName) == null)
            return 0;
        return dao.findCurrentVolumeOfStock(stockName);
    }

    @Override
    public List<String> findDistinctByStockName() {
        return dao.getDistinctStockName();
    }

    @Override
    public Transaction getLatestTransactionById(Integer stratId) {
        return dao.getLatestTransactionById(stratId);
    }


}
