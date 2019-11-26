package com.citi.training.controller;

import com.citi.training.entities.Transaction;
import com.citi.training.services.TransactionService;
import com.citi.training.utils.WitiUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;

@RestController
@RequestMapping("/viewDetails")
@CrossOrigin(origins = "http://localhost:3000")
public class DetailsController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private WitiUtil witiUtil;

    @RequestMapping(value="/strategyHistory", method= RequestMethod.GET)
    public String addStrategy(HttpServletRequest request) throws JSONException {
        JSONArray resultArray = new JSONArray();
        Integer stratId = Integer.parseInt(request.getParameter("id"));
        ArrayList<Transaction> transactions = transactionService.getTransactionsByStrategyId(stratId);
        Collections.sort(transactions);
        if(!transactions.isEmpty()){
            for(Transaction trans : transactions) {
                JSONObject result = new JSONObject();
                    result.put("id", trans.getId());
                    result.put("action", trans.getStockPosition());
                    result.put("time", trans.getTransactionTime());
                    result.put("price", trans.getTransactionPrice());
                    result.put("performance", trans.getProfit());
                    resultArray.put(result);
            }
        }
        return resultArray.toString();
    }

    @RequestMapping(value="/getGraphByStrategyId", method= RequestMethod.GET)
    public String getGraphByStrategyId(HttpServletRequest request) throws JSONException {
        Integer stratId = Integer.parseInt(request.getParameter("id"));
        ArrayList<Transaction> transactions = transactionService.getTransactionsByStrategyIdAndStockPosition(stratId, "SELL");

        return witiUtil.formatDataForStratDetailsProfitGraph(transactions);
    }



}
