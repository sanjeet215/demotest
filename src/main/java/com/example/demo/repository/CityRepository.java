package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.City;
import com.example.demo.model.State;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

	List<City> findByState(State state);
}
