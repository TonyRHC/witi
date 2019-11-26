package com.citi.training.services;

import com.citi.training.entities.TwoMovingAverages;

import java.util.List;

public interface TwoMovingAveragesService {
    List<TwoMovingAverages> getAllData();
    void addTwoMovingAverages(TwoMovingAverages twoMovingAverages);
    TwoMovingAverages getTwoMovingAveragesById(Integer id);
}
