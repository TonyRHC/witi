package com.citi.training.services;

import com.citi.training.entities.Strategy;

import java.util.ArrayList;
import java.util.List;


public interface StrategyService {
    List<Strategy> getAllStrategies();
    List<Strategy> getAllStrategiesByTypeDesc(String type);
    void addStrategy(Strategy strategy);
    Strategy getStrategyById(Integer id);
    ArrayList<Strategy> getAllStrategiesByType(String type);
    Integer findCurrentVolumeOfStrategy(String type);
    Iterable<String> findDistinctStrategyType();
    Integer findCurrentCountOfStrategy(String type);
    void updateStatusById(String status, Integer id);
}

