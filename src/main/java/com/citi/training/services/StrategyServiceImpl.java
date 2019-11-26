package com.citi.training.services;

import com.citi.training.entities.Strategy;
import com.citi.training.repositories.StrategyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StrategyServiceImpl implements StrategyService {

    @Autowired
    private StrategyRepo dao;

    @Override
    public List<Strategy> getAllStrategies() { return dao.findAll(); }

    @Override
    public List<Strategy> getAllStrategiesByTypeDesc(String type) {
        return dao.findAllByStrategyTypeOrderByInputTimeDesc(type);
    }

    @Override
    public void addStrategy(Strategy strategy) {
        dao.save(strategy);
    }

    @Override
    public Strategy getStrategyById(Integer id) {
        return dao.findStrategyById(id);
    }

    @Override
    public ArrayList<Strategy> getAllStrategiesByType(String type) {
        return dao.findStrategiesByStrategyType(type);
    }

    @Override
    public Integer findCurrentVolumeOfStrategy(String type) {
        return dao.findCurrentVolumeOfStrategy(type);
    }

    @Override
    public Iterable<String> findDistinctStrategyType() {
        return dao.getDistinctStrategyType();
    }

    @Override
    public Integer findCurrentCountOfStrategy(String type) {
        return dao.findCurrentCountOfStrategy(type);
    }

    @Override
    public void updateStatusById(String status, Integer id) {
        Strategy strat = dao.findStrategyById(id);
        strat.setStatus(status);
        dao.save(strat);
    }


}
