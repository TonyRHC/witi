package com.citi.training.repositories;

import com.citi.training.entities.TwoMovingAverages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TwoMovingAveragesRepo extends JpaRepository<TwoMovingAverages, Integer> {

    TwoMovingAverages findTwoMovingAveragesById(Integer id);
}
