package com.citi.training.utils;

import com.citi.training.entities.Transaction;
import com.citi.training.services.StrategyService;
import com.citi.training.services.TransactionService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

@Service
public class WitiUtil {

    @Autowired
    StrategyService strategyService;

    @Autowired
    TransactionService transactionService;

    public static String getCurrentTime(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return (sdf.format(cal.getTime()));
    }

    public int calculateProfitAtTime(String timeTransaction) {
        int profit= 0;
        int numOfStrategies = strategyService.getAllStrategies().size();
        for (int i = 1; i <= numOfStrategies; i++){
            Transaction latestSellTransaction = transactionService.getLatestSellTransactionByIdAndTime(i, timeTransaction);
            if (latestSellTransaction != null) {
                profit += latestSellTransaction.getProfit();
            }
        }

        return profit;
    }

    public String formatDataForGraph(List<Transaction> transactions) throws JSONException {
        int size = transactions.size();
        Collections.sort(transactions);
        JSONArray jsonArray = new JSONArray();
        if (size > 20){
            double jump = size/20.0;
            for (int i = 0; i < 20; i++){
                int x = (int) Math.floor(i * jump);
                JSONObject jsonObject = new JSONObject();
                String timeTransaction = transactions.get(x).getTransactionTime();
                jsonObject.put("name", timeTransaction);
                jsonObject.put("P/L", calculateProfitAtTime(timeTransaction));
                jsonArray.put(jsonObject);
            }
            JSONObject jsonObject = new JSONObject();
            String timeTransaction = transactions.get(transactions.size()-1).getTransactionTime();
            jsonObject.put("name", timeTransaction);
            jsonObject.put("P/L",  calculateProfitAtTime(timeTransaction));
            jsonArray.put(jsonObject);
        }
        else{
            for (int i = 0; i < transactions.size(); i++) {
                JSONObject jsonObject = new JSONObject();
                String timeTransaction = transactions.get(i).getTransactionTime();
                jsonObject.put("name", timeTransaction);
                jsonObject.put("P/L", calculateProfitAtTime(timeTransaction));
                jsonArray.put(jsonObject);
            }
        }
        return jsonArray.toString();
    }

    public String formatDataForStratProfitGraph(List<Transaction> transactions) throws JSONException {
        int size = transactions.size();
        Collections.sort(transactions);
        JSONArray jsonArray = new JSONArray();
        if (size > 20){
            double jump = size/20.0;
            System.out.println(jump);
            for (int i = 0; i < 20; i++){
                int x = (int) Math.floor(i * jump);
                JSONObject jsonObject = new JSONObject();
                String timeTransaction = transactions.get(x).getTransactionTime();
                jsonObject.put("name", timeTransaction);
                jsonObject.put("P/L", calculateProfitAtTime(timeTransaction));
                jsonArray.put(jsonObject);
            }
            JSONObject jsonObject = new JSONObject();
            String timeTransaction = transactions.get(transactions.size()-1).getTransactionTime();
            jsonObject.put("name", timeTransaction);
            jsonObject.put("P/L",  calculateProfitAtTime(timeTransaction));
            jsonArray.put(jsonObject);
        }
        else{
            for (int i = 0; i < transactions.size(); i++) {
                JSONObject jsonObject = new JSONObject();
                String timeTransaction = transactions.get(i).getTransactionTime();
                jsonObject.put("name", timeTransaction);
                jsonObject.put("P/L", calculateProfitAtTime(timeTransaction));
                jsonArray.put(jsonObject);
            }
        }
        return jsonArray.toString();
    }

    public String formatDataForStratDetailsProfitGraph(ArrayList<Transaction> transactions) throws JSONException {
        int size = transactions.size();
        Collections.sort(transactions);
        double profit = 0;
        JSONArray jsonArray = new JSONArray();
        if (size > 20){
            double jump = size/20.0;
            for (int i = 0; i < 20; i++){
                int x = (int) Math.floor(i * jump);
                JSONObject jsonObject = new JSONObject();
                String timeTransaction = transactions.get(x).getTransactionTime();
                jsonObject.put("name", timeTransaction);
                jsonObject.put("P/L", transactions.get(x).getProfit());
                jsonArray.put(jsonObject);
            }
            JSONObject jsonObject = new JSONObject();
            String timeTransaction = transactions.get(transactions.size()-1).getTransactionTime();
            jsonObject.put("name", timeTransaction);
            jsonObject.put("P/L", transactions.get(transactions.size()-1).getProfit());
            jsonArray.put(jsonObject);
        }
        else{
            for (int i = 0; i < transactions.size(); i++) {
                JSONObject jsonObject = new JSONObject();
                String timeTransaction = transactions.get(i).getTransactionTime();
                jsonObject.put("name", timeTransaction);
                jsonObject.put("P/L", transactions.get(i).getProfit());
                jsonArray.put(jsonObject);
            }
        }
        return jsonArray.toString();

    }
}
