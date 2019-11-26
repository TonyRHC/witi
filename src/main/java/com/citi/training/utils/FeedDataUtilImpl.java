package com.citi.training.utils;

import com.citi.training.pojos.FeedDataPojo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class FeedDataUtilImpl implements FeedDataUtil {

    @Override
    public FeedDataPojo getCurrentPrice(String symbol) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            URL jsonURI = new URL("http://nyc31.conygre.com:31/Stock/getStockPrice/" + symbol);
            FeedDataPojo feedDataPojo = objectMapper.readValue(jsonURI, FeedDataPojo.class);
            return feedDataPojo;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<FeedDataPojo> getStockPricesInTime(String symbol, int time) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            URL jsonURI = new URL("http://nyc31.conygre.com:31/Stock/getStockPriceList/" + symbol + "?howManyValues=" + time);
            ArrayList<FeedDataPojo> feedDataPojoObjects = objectMapper.readValue(jsonURI, objectMapper.getTypeFactory().constructCollectionType(List.class, FeedDataPojo.class));
            return feedDataPojoObjects;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
