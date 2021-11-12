package com.teafarm.production.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teafarm.production.entity.Weight;

@Repository
public interface WeightRepo extends JpaRepository<Weight,Integer> {

}
