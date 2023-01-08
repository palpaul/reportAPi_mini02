package com.rahul.reportapi.services;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.rahul.reportapi.entityBinding.CitizenPlan;
import com.rahul.reportapi.entityBinding.SearchRequest;

//import jakarta.servlet.http.HttpServletResponse;

@Service
public interface ReportService {
	
	public List<String> getPlandetails();
	public List<String> getPlanStaus();
	//based on user search cretirea we need one search method okk 
	public List<CitizenPlan> getCitizensPlan(SearchRequest searchRequest);
	public void exportExcel(HttpServletResponse response) throws Exception;
	public void exportPdf(HttpServletResponse response) throws Exception;

	
	//insert some data into table
	
	public String InsertData(CitizenPlan ctplan);
}
