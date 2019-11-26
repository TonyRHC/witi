package com.citi.training.pojos;

public class TMAPojo implements AddStrategyPojo {
    private String symbol;
    private String volume;
    private String longTime;
    private String shortTime;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getLongTime() {
        return longTime;
    }

    public void setLongTime(String longTime) {
        this.longTime = longTime;
    }

    public String getShortTime() {
        return shortTime;
    }

    public void setShortTime(String shortTime) {
        this.shortTime = shortTime;
    }

    @Override
    public String toString() {
        return "TMAPojo{" +
                "symbol='" + symbol + '\'' +
                ", volume='" + volume + '\'' +
                ", longTime='" + longTime + '\'' +
                ", shortTime='" + shortTime + '\'' +
                '}';
    }
}
