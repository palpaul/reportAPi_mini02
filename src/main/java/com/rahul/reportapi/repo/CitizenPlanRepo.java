package com.rahul.reportapi.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rahul.reportapi.entityBinding.CitizenPlan;

public interface CitizenPlanRepo extends 	JpaRepository<CitizenPlan, Serializable>{

	
	//for our requirement we need to write a custom method to searvh distinct  plansname okk
	
	@Query("select distinct(planName) from CitizenPlan")
	public List<String > getPlanNames();
	
	@Query("select distinct (planStatus) from CitizenPlan")
	List<String> getPlanStatuses();
	
//	
//	@Query("select * from CitizenPlan where planName=:planName")
//	List<CitizenPlan> getRecords();
}
