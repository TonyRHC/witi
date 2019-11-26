package com.citi.training.algorithms;

import com.citi.training.entities.Strategy;
import com.citi.training.entities.Transaction;
import com.citi.training.entities.TwoMovingAverages;
import com.citi.training.pojos.FeedDataPojo;
import com.citi.training.services.StrategyService;
import com.citi.training.services.TransactionService;
import com.citi.training.services.TwoMovingAveragesService;
import com.citi.training.utils.FeedDataUtil;
import com.citi.training.utils.WitiUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
@EnableJms
public class TwoMovingAveragesAlgo {
    @Autowired
    private StrategyService strategyService;

    @Autowired
    private TwoMovingAveragesService twoMovingAveragesService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private JmsTemplate jmsTemplate2;

    @Autowired
    private FeedDataUtil feedData;
    private static final Logger log = LoggerFactory.getLogger(TwoMovingAverages.class);

    private List<TwoMovingAverages> tmas;
    private static double PROFIT_MARGIN = 1.01; //Sell when current price is 1.01 times the buy price, 1% profit
    private static double PROFIT_LOSS = 0.9; //Sell and stop strategy when current price is .9 times the buy price, 10% loss


    public Iterable<TwoMovingAverages> getTmas() {
        return tmas;
    }

    public void setTmas(List<TwoMovingAverages> tmas) {
        this.tmas = tmas;
    }

    private void updateListOfTmas() {
        setTmas(twoMovingAveragesService.getAllData());
    }

    public void runAlgorithm () {
        //Get all the TMAS
        updateListOfTmas();
        //Process each TMA
        for (TwoMovingAverages tma : tmas) {
            int id = tma.getId();

            Strategy strat = strategyService.getStrategyById(id); //one-to-one mapping of primary keys
            if(!strat.getStatus().equals("running"))
                continue;
            checkCross(strat, tma);
            process(strat, tma, id);
        }
    }

    public void checkCross(Strategy strat, TwoMovingAverages tma){
        if(strat.getCrossed().equals("y"))
            return;

        String stock = strat.getStockName();
        int longTime = tma.getLongTime();
        int shortTime = tma.getShortTime();

        double shortAverage = getAveragePrice(stock, shortTime);
        double longAverage = getAveragePrice(stock,longTime);

        transactionService.getLatestTransactionById(strat.getId());

        if (transactionService.getLatestTransactionById(strat.getId()).getStockPosition().equals("SELL") && longAverage > shortAverage){
            strat.setCrossed("y");
            strategyService.addStrategy(strat);
            //System.out.println("Strat with id: " + strat.getId() + " now available to buy again!");
        }
        return;
    }

    public void process(Strategy strat, TwoMovingAverages tma, int id) {
        //Get the stock name and times
        String stock = strat.getStockName();
        int longTime = tma.getLongTime();
        int shortTime = tma.getShortTime();

        //Calculate the averages and the current price with the stock name and times
        double shortAverage = getAveragePrice(stock, shortTime);
        double longAverage = getAveragePrice(stock,longTime);
        double currentPrice = feedData.getCurrentPrice(stock).getPrice();

        double tranPrice = 0;
        boolean hasTransactions = true , justBought = false;

        //Get the latest transaction
        Transaction latest = transactionService.getLatestTransactionById(id);
        //First check if latest is null (Check if any transactions actually exist yet)
        if (latest != null) {
            //Transaction exists: Get the latest transaction's price and the recent position
            tranPrice = latest.getTransactionPrice();
            justBought = lastWasBought(id);

        }
        else {
            hasTransactions = false; //No transactions exist
        }

        //If most recent transaction is a buy, look to sell
        if (justBought && hasTransactions) {

            //needs to check price to sell
            double onePercentGain = tranPrice * PROFIT_MARGIN;
            double tenPercentLoss = tranPrice * PROFIT_LOSS;
            //System.out.println("SELL AT THIS AMOUNT FOR " + stock + " : " + onePercentGain + " FOR PROPFIT OR BAIL AT: " + tenPercentLoss);

            if (currentPrice >= onePercentGain) { //1% gain = SELL
                jmsTemplate.convertAndSend("queue/SellQueue", "Sell message has been received by broker.");
                sell(id, currentPrice);
                strat.setCrossed("n");
                strategyService.addStrategy(strat);
            } else if (currentPrice <= tenPercentLoss) { //10% loss = SELL AND STOP
                jmsTemplate.convertAndSend("queue/SellQueue", "Sell message has been received by broker.");
                sell(id, currentPrice);
                stop(id);
            }
        }
        else { //Look to buy if no transactions or most recent one is a SELL
            if (shortAverage > longAverage && strat.getCrossed().equals("y")) {
                //BROKER HERE
                jmsTemplate2.convertAndSend("queue/BuyQueue", "Buy message has been received by broker.");

                buy(id, currentPrice, 0);
            }
        }

        //System.out.println("ID: " + id + " | Currrent: " + currentPrice + " | Short: " + shortAverage + " Long: " + longAverage);

    }

    private double getAveragePrice(String stock, int time) {
        ArrayList<FeedDataPojo> feed = feedData.getStockPricesInTime(stock, time);
        double shortAverage = 0;
        for (FeedDataPojo pojo : feed) {
            shortAverage += pojo.getPrice();
        }
        shortAverage /= feed.size();

        //System.out.println("Average calculated for stock: " + stock + " , with time: "+ time + " is "+ shortAverage);
        return shortAverage;
    }

    private boolean lastWasBought(int id) {
        Transaction latest = transactionService.getLatestTransactionById(id);
        if (latest.getStockPosition().equals("BUY")) return true;
        else return false;
    }

    private Transaction getLatestTransaction(int id) {
        return transactionService.getLatestTransactionById(id);
    }

    private void sell(int id, double priceNow ) {
        Transaction latestTransaction = getLatestTransaction(id);
        Strategy strategy = strategyService.getStrategyById(id);

        System.err.println("ID: " + id + " | " + latestTransaction);

        priceNow = Math.round(priceNow* 100.0)/100.0;
        double newProfit = (Math.round(latestTransaction.getProfit() + priceNow * strategy.getVolume()) * 100.0)/100.0;

        Transaction sellTransaction = new Transaction(
                latestTransaction.getStrategyId(),
                latestTransaction.getStockName(),
                "SELL",
                priceNow,
                newProfit,
                "Y",
                WitiUtil.getCurrentTime()
        );

        transactionService.addTransaction(sellTransaction);

        latestTransaction.setIsComplete("Y");
        transactionService.addTransaction(latestTransaction);

        //System.out.println("SELL just happened for id: " + id + " price: " + priceNow);

    }

    private void buy(int id, double priceNow, double difference) {
        Transaction lastestTransaction = getLatestTransaction(id);
        Strategy strategy = strategyService.getStrategyById(id);

        priceNow = Math.round(priceNow* 100.0)/100.0;
        double newProfit= Math.round(priceNow * strategy.getVolume() * -1 * 100.0) / 100.0;

        if (lastestTransaction != null) {
            newProfit = (Math.round(lastestTransaction.getProfit() + (priceNow * strategy.getVolume() * -1)) * 100.0)/100.0;
        }
        //Add buy to transaction table
        Transaction buyTransaction = new Transaction(
                id,
                strategyService.getStrategyById(id).getStockName(),
                "BUY",
                priceNow,
                newProfit,
                "N",
                WitiUtil.getCurrentTime()
        );

        transactionService.addTransaction(buyTransaction);


        //System.out.println("BUY just happened for id: " + id + " price: " + priceNow);
    }

    private void stop(int id) {
        strategyService.updateStatusById("stopped", id);
    }

}
