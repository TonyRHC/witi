package com.citi.training.threads;

import com.citi.training.algorithms.TwoMovingAveragesAlgo;
import com.citi.training.entities.Strategy;
import com.citi.training.services.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@EnableJms
public class ScheduledThread {

    @Autowired
    private StrategyService strategyService;

    @Autowired
    TwoMovingAveragesAlgo tmaAlgo;

    private static final Logger log = LoggerFactory.getLogger(ScheduledThread.class);

    @Scheduled(fixedRate=1000)
    public void scheduledTMA() {
        List<Strategy> strategies = strategyService.getAllStrategies();
        strategies.stream().forEach(
                strat -> tmaAlgo.runAlgorithm()
        );
    }
}
