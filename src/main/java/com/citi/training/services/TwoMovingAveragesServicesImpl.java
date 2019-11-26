package com.citi.training.services;

import com.citi.training.entities.TwoMovingAverages;
import com.citi.training.repositories.TwoMovingAveragesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TwoMovingAveragesServicesImpl implements TwoMovingAveragesService {

    @Autowired
    private TwoMovingAveragesRepo dao;

    @Override
    public List<TwoMovingAverages> getAllData() {
        return dao.findAll();
    }

    @Override
    public void addTwoMovingAverages(TwoMovingAverages twoMovingAverages) {
        dao.save(twoMovingAverages);
    }

    @Override
    public TwoMovingAverages getTwoMovingAveragesById(Integer id) {
        return dao.findTwoMovingAveragesById(id);
    }

}
