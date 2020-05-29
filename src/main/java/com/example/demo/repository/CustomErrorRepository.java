package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CustomErrors;

@Repository
public interface CustomErrorRepository extends JpaRepository<CustomErrors, Long> {

}
