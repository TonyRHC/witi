package com.citi.training.entities;

import javax.persistence.*;


@Entity @Table(name="strategy")
public class Strategy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "stock_name")
    private String stockName;
    @Column(name = "volume")
    private Integer volume;
    @Column(name = "input_time")
    private String inputTime;
    @Column(name = "strategy_type")
    private String strategyType;
    @Column(name = "status")
    private String status;
    @Column(name = "crossed")
    private String crossed;

    public Strategy(){}

    public Strategy(String stockName, Integer volume, String inputTime, String strategyType, String status, String crossed) {
        this.stockName = stockName;
        this.volume = volume;
        this.strategyType = strategyType;
        this.status = status;
        this.inputTime = inputTime;
        this.crossed = crossed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public String getInputTime() {
        return inputTime;
    }

    public void setInputTime(String inputTime) {
        this.inputTime = inputTime;
    }

    public String getStrategyType() {
        return strategyType;
    }

    public void setStrategyType(String strategyType) {
        this.strategyType = strategyType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCrossed() {
        return crossed;
    }

    public void setCrossed(String crossed) {
        this.crossed = crossed;
    }

    @Override
    public String toString() {
        return "Strategy{" +
                "id=" + id +
                ", stockName='" + stockName + '\'' +
                ", volume=" + volume +
                ", inputTime='" + inputTime + '\'' +
                ", strategyType='" + strategyType + '\'' +
                ", status='" + status + '\'' +
                ", crossed='" + crossed + '\'' +
                '}';
    }
}