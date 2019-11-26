package com.citi.training.controller;

import com.citi.training.entities.Transaction;
import com.citi.training.services.StrategyService;
import com.citi.training.services.TransactionService;
import com.citi.training.utils.FeedDataUtil;
import com.citi.training.utils.WitiUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("home")
@CrossOrigin(origins = "http://localhost:3000")
public class HomePageController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    StrategyService strategyService;

    @Autowired
    FeedDataUtil feedDataUtil;

    @Autowired
    WitiUtil witiUtil;

    @RequestMapping(value="/getVolumeStock", method= RequestMethod.GET)
    public String getPieGraphOverallVolumeStock() throws JSONException {
        Iterable<String> t = transactionService.findDistinctByStockName();
        JSONArray resultArray = new JSONArray();
        for (String s : t){
            int volume = transactionService.findCurrentVolumeOfStock(s);
            if (volume == 0)
                continue;
            JSONObject result = new JSONObject();
            result.put("name", s);
            result.put("volume", volume);
            resultArray.put(result);
        }
        return resultArray.toString();
    }

    @RequestMapping(value="/getVolumeStrategy", method= RequestMethod.GET)
    public String getPieGraphOverallVolumeStrategy() throws JSONException {
        JSONArray resultArray = new JSONArray();
        Iterable<String> stratTypes = strategyService.findDistinctStrategyType();
        for (String type: stratTypes){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", type);
            Integer vol = strategyService.findCurrentVolumeOfStrategy(type);
            jsonObject.put("volume" , vol);
            resultArray.put(jsonObject);
        }
        return resultArray.toString();
    }

    @RequestMapping(value="/getOverallProfit", method= RequestMethod.GET)
    public String getGraphOverallProfit() throws JSONException {
        List<Transaction> transactions = transactionService.getAllTransactions();
        ArrayList<Transaction> allSelltransactions = new ArrayList<Transaction>();
        for (Transaction t: transactions){
            if (t.getStockPosition().equals("SELL")) {
                allSelltransactions.add(t);
            }
        }

        return witiUtil.formatDataForGraph(allSelltransactions);
    }

}
