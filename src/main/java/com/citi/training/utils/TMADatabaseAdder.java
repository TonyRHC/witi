package com.citi.training.utils;

import com.citi.training.entities.Strategy;
import com.citi.training.entities.TwoMovingAverages;
import com.citi.training.pojos.AddStrategyPojo;
import com.citi.training.pojos.TMAPojo;
import com.citi.training.services.StrategyService;
import com.citi.training.services.TwoMovingAveragesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TMADatabaseAdder {

    @Autowired
    private StrategyService strategyService;

    @Autowired
    private TwoMovingAveragesService twoMovingAveragesService;

    public void addTMA(AddStrategyPojo pojo) {
        TMAPojo tmaPojo = (TMAPojo) pojo;

        System.err.println(pojo);

        String symbol = tmaPojo.getSymbol().toUpperCase();
        int volume = Integer.parseInt(tmaPojo.getVolume());
        int longTime = Integer.parseInt(tmaPojo.getLongTime());
        int shortTime = Integer.parseInt(tmaPojo.getShortTime());

        String time = WitiUtil.getCurrentTime();

        Strategy strategy = new Strategy(symbol, volume, time, "tma", "running", "y");

        strategyService.addStrategy(strategy);

        TwoMovingAverages tma = new TwoMovingAverages(strategy.getId(), longTime, shortTime, time);

        twoMovingAveragesService.addTwoMovingAverages(tma);
    }
}
