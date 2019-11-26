package com.citi.training.repositories;

import com.citi.training.entities.Strategy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface StrategyRepo extends JpaRepository<Strategy, Integer> {

    Strategy findStrategyById(Integer id);
    ArrayList<Strategy> findStrategiesByStrategyType(String type);

    ArrayList<Strategy> findAllByStrategyTypeOrderByInputTimeDesc(String type);


    @Query("select sum(strat.volume) from Transaction trans, Strategy strat where trans.strategyId = strat.id and strat.strategyType = ?1 and trans.stockPosition = 'BUY' and trans.isComplete = 'n'")
    Integer findCurrentVolumeOfStrategy(String strategyType);

    @Query("select count(*) from Transaction trans, Strategy strat where trans.strategyId = strat.id and strat.strategyType = ?1 and trans.stockPosition = 'BUY' and trans.isComplete = 'n'")
    Integer findCurrentCountOfStrategy(String strategyType);

    @Query(value = "SELECT DISTINCT(strat.strategy_type) from strategy strat", nativeQuery = true)
    List<String> getDistinctStrategyType();

}

