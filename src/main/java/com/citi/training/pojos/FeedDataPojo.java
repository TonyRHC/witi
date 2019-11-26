package com.citi.training.pojos;

public class FeedDataPojo {
    private String symbol;
    private Integer symbolID;
    private double price;
    private String theTime;
    private int periodNumber;
    private String companyName;

    public FeedDataPojo(String symbol, Integer symbolID, double price, String theTime, int periodNumber, String companyName) {
        this.symbol = symbol;
        this.symbolID = symbolID;
        this.price = price;
        this.theTime = theTime;
        this.periodNumber = periodNumber;
        this.companyName = companyName;
    }

    public FeedDataPojo(){}

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getSymbolID() {
        return symbolID;
    }

    public void setSymbolID(Integer symbolID) {
        this.symbolID = symbolID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTheTime() {
        return theTime;
    }

    public void setTheTime(String theTime) {
        this.theTime = theTime;
    }

    public int getPeriodNumber() {
        return periodNumber;
    }

    public void setPeriodNumber(int periodNumber) {
        this.periodNumber = periodNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "FeedDataPojo{" +
                "symbol='" + symbol + '\'' +
                ", symbolID=" + symbolID +
                ", price=" + price +
                ", theTime='" + theTime + '\'' +
                ", periodNumber=" + periodNumber +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
