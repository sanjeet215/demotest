package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.JobDetails;

@Repository
public interface JobDetailsRepository extends JpaRepository<JobDetails,Long>{

}
