package com.citi.training.utils;

import com.citi.training.pojos.FeedDataPojo;

import java.util.ArrayList;

public interface FeedDataUtil {
    FeedDataPojo getCurrentPrice(String symbol);
    ArrayList<FeedDataPojo> getStockPricesInTime(String symbol, int time);
}
