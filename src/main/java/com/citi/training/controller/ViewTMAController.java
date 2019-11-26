package com.citi.training.controller;

import com.citi.training.entities.Strategy;
import com.citi.training.entities.Transaction;
import com.citi.training.services.StrategyService;
import com.citi.training.services.TransactionService;
import com.citi.training.services.TwoMovingAveragesService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("TMA")
public class ViewTMAController {

    @Autowired
    private StrategyService strategyService;

    @Autowired
    private TwoMovingAveragesService twoMovingAveragesService;

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value="/getTMA", method=RequestMethod.GET)
    public String getAllTMAStrategies() throws JSONException {
        JSONArray resultArray = new JSONArray();
        List<Strategy> tmaStrategies = strategyService.getAllStrategiesByTypeDesc("tma");
        for (Strategy tmaStrat: tmaStrategies){
            String status = tmaStrat.getStatus();
            JSONObject result = new JSONObject();
            double profit = 0;
            Transaction transaction = transactionService.getLatestSellTransactionById(tmaStrat.getId());
            if(transaction != null){
                profit = transaction.getProfit();
            }

            result.put("id", tmaStrat.getId());
            result.put("stockNames", tmaStrat.getStockName());
            result.put("volume", tmaStrat.getVolume());
            result.put("shortTime", twoMovingAveragesService.getTwoMovingAveragesById(tmaStrat.getId()).getShortTime());
            result.put("longTime", twoMovingAveragesService.getTwoMovingAveragesById(tmaStrat.getId()).getLongTime());
            result.put("profits", profit);
            result.put("status", status);
            resultArray.put(result);
        }
        return resultArray.toString();
    }

    @RequestMapping(value="/stopTMA", method=RequestMethod.POST)
    public String stopTMAStrategy(RequestEntity<String> req) throws JSONException {
        String s = req.getBody().toString();
        String idString = s.substring(s.indexOf(":")+1, s.indexOf("}"));
        int id = Integer.parseInt(idString);
        strategyService.updateStatusById("stopped", id);
        return "stopped " + id;
    }

    @RequestMapping(value="/pauseTMA", method=RequestMethod.POST)
    public String pauseTMAStrategy(RequestEntity<String> req) throws JSONException {
        String s = req.getBody().toString();
        String idString = s.substring(s.indexOf(":")+1, s.indexOf("}"));
        int id = Integer.parseInt(idString);
        strategyService.updateStatusById("paused", id);
        return "paused " + id;
    }

    @RequestMapping(value="/runTMA", method=RequestMethod.POST)
    public String runTMAStrategy(RequestEntity<String> req) throws JSONException {
        String s = req.getBody().toString();
        String idString = s.substring(s.indexOf(":")+1, s.indexOf("}"));
        int id = Integer.parseInt(idString);
        strategyService.updateStatusById("running", id);
        return "running " + id;
    }



    public double calculateStrategyProfit(ArrayList<Double> transactionProfits) {
        Double profit = 0.0;
        for (Double d : transactionProfits) {
            profit += d;
        }
        return profit;
    }
}
