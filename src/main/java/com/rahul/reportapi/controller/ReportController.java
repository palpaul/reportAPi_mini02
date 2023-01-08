package com.rahul.reportapi.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rahul.reportapi.entityBinding.CitizenPlan;
import com.rahul.reportapi.entityBinding.SearchRequest;
import com.rahul.reportapi.servicesImpl.ReportServiceImpl;

//import jakarta.servlet.http.HttpServletResponse;

@RestController
public class ReportController {
	
	@Autowired
	private ReportServiceImpl reportServiceImpl;
	
	
	@GetMapping("/about")
	public String getMsg() {
		return "Hey welcome to Report API";
	}
	
	
	// insert some data
	@PostMapping("/insertdata")
	public String InsertData(@RequestBody CitizenPlan data) {
		return reportServiceImpl.InsertData(data);
	}
	
	@GetMapping("/plan")
	public ResponseEntity<List<String>> getPlans(){
		List<String > plannams= reportServiceImpl.getPlandetails();
		return new ResponseEntity<>(plannams,HttpStatus.OK);
	}
	
	
	@GetMapping("/getplan")
	public List<String>getPlan(){
		List<String > plannams= reportServiceImpl.getPlandetails();
		return  plannams;
	}
	
	@GetMapping("/planstatus")
	public ResponseEntity< List<String>> getPlanStaus(){
		List<String > planstatus =  reportServiceImpl.getPlanStaus();
		return new ResponseEntity<>(planstatus,HttpStatus.OK);
	}
	//based on user search cretirea we need one search method okk 
	
//	@GetMapping("/search")
//	public List<CitizenPlan> getCitizensPlan(SearchRequest searchRequest){
//		return reportServiceImpl.getCitizensPlan(searchRequest);
//	}
	
	
	//recommended approach for search
	@PostMapping("/searchplan")
	public ResponseEntity< List<CitizenPlan>> search(@RequestBody SearchRequest searchRequest){
		List<CitizenPlan>citizenPlans =  reportServiceImpl.getCitizensPlan(searchRequest);
		return new ResponseEntity<>(citizenPlans,HttpStatus.OK);
	}
	
	@GetMapping("/excel")
	public void exportExcel(HttpServletResponse response) throws Exception{
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String HeaderValue = "attachment;filename = Citizen_INFO.xlsx";
		response.setHeader(headerKey, HeaderValue);
		reportServiceImpl.exportExcel(response);
		response.flushBuffer();
	}
	
	@GetMapping("/pdf")
	public void exportPdf(HttpServletResponse response) throws Exception{
		
		response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename= Citizen_InfO"+"_"+ currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        reportServiceImpl.exportPdf(response);
        response.flushBuffer();
        	
//        UserPDFExporter exporter = new UserPDFExporter(listUsers);
//        exporter.export(response);
        
		
	}


}
