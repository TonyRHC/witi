package com.citi.training.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity @Table(name="transaction")
public class Transaction implements Serializable, Comparable<Transaction>{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="strategy_id")
    private int strategyId;
    @Column(name="transaction_price") private Double transactionPrice;
    @Column(name="stock_name") private String stockName;
    @Column(name="stock_position") private String stockPosition;
    @Column(name="profit") private Double profit;
    @Column(name="is_complete") private String isComplete;
    @Column(name="transaction_time") private String transactionTime;

    public Transaction() {}



    public Transaction(int strategyId, String stockName, String stockPosition, Double transactionPrice, Double profit, String isComplete, String transactionTime) {
        this.strategyId = strategyId;
        this.stockName = stockName;
        this.stockPosition = stockPosition;
        this.transactionPrice = transactionPrice;
        this.profit = profit;
        this.isComplete = isComplete;
        this.transactionTime = transactionTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(int strategyId) {
        this.strategyId = strategyId;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockPosition() {
        return stockPosition;
    }

    public void setStockPosition(String stockPosition) {
        this.stockPosition = stockPosition;
    }

    public Double getTransactionPrice() {
        return transactionPrice;
    }

    public void setTransactionPrice(Double transactionPrice) {
        this.transactionPrice = transactionPrice;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public String getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(String isComplete) {
        this.isComplete = isComplete;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    @Override
    public int compareTo(Transaction compareTrans) {
        String time=((Transaction)compareTrans).getTransactionTime();
        /* For Ascending order*/
        if (this.getTransactionTime().compareTo(time) >= 0){
            return 1;
        }
        else
            return -1;

        /* For Descending order do like this */
        //return compareage-this.studentage;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", strategyId=" + strategyId +
                ", stockName='" + stockName + '\'' +
                ", stockPosition='" + stockPosition + '\'' +
                ", profit=" + profit +
                ", isComplete='" + isComplete + '\'' +
                ", transactionTime='" + transactionTime + '\'' +
                '}';
    }


}
