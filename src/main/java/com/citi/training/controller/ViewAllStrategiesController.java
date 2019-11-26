package com.citi.training.controller;

import com.citi.training.entities.Strategy;
import com.citi.training.entities.Transaction;
import com.citi.training.services.StrategyService;
import com.citi.training.services.TransactionService;
import com.citi.training.utils.WitiUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
@RequestMapping("viewAll")
@CrossOrigin(origins = "http://localhost:3000")
public class ViewAllStrategiesController {

    @Autowired
    TransactionService transactionService;
    @Autowired
    StrategyService strategyService;
    @Autowired
    WitiUtil witiUtil;

    @RequestMapping(value="/getTMAVolumeAndUniqueStocks", method= RequestMethod.GET)
    public String getOverallVolumeStrategy() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        Integer vol = strategyService.findCurrentVolumeOfStrategy("tma");
        jsonObject.put("volume", vol);
        Integer count = strategyService.findCurrentCountOfStrategy("tma");
        jsonObject.put("count", count);
        return jsonObject.toString();
    }

    @RequestMapping(value="/getTMAGraph", method= RequestMethod.GET)
    public String getOverallTMAProfitGraph() throws JSONException {
        ArrayList<Transaction> allTMAtransactions = new ArrayList<Transaction>();
        ArrayList<Strategy> strategies = strategyService.getAllStrategiesByType("tma");
        for (Strategy strat: strategies){
            ArrayList<Transaction> transactions = transactionService.getTransactionsByStrategyId(strat.getId());
            for (Transaction t: transactions){
                if (t.getStockPosition().equals("SELL")) {
                    allTMAtransactions.add(t);
                }
            }
        }
        return witiUtil.formatDataForStratProfitGraph(allTMAtransactions);
    }



}
