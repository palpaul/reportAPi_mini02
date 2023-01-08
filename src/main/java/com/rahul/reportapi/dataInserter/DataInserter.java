package com.rahul.reportapi.dataInserter;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.rahul.reportapi.entityBinding.CitizenPlan;
import com.rahul.reportapi.repo.CitizenPlanRepo;

@Component
public class DataInserter implements ApplicationRunner{

	//runner is execute the logic once when project is started
	@Autowired
	private CitizenPlanRepo cprepo;
	
	
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		
		CitizenPlan cp1 = new CitizenPlan();
					cp1.setCname("John");
					cp1.setCemail("john@gmail.com");
					cp1.setGender("Male");
					cp1.setPhno(9999999L);
					cp1.setPlanName("acqua");
					cp1.setPlanStatus("Denied");
					cp1.setSsn(1212212121L);
					
					CitizenPlan cp2 = new CitizenPlan();
					cp2.setCname("Hokay");
					cp2.setCemail("hokay@gmail.com");
					cp2.setGender("Male");
					cp2.setPhno(8899999L);
					cp2.setPlanName("snap");
					cp2.setPlanStatus("Denied");
					cp2.setSsn(9992212121L);
					
					CitizenPlan cp3 = new CitizenPlan();
					cp3.setCname("BlackWIdow");
					cp3.setCemail("blackWidow@gmail.com");
					cp3.setGender("FeMale");
					cp3.setPhno(3339999L);
					cp3.setPlanName("life insu");
					cp3.setPlanStatus("Approved");
					cp3.setSsn(87872212121L);
					
					List<CitizenPlan> finalList  = Arrays.asList(cp1,cp2,cp3);
					cprepo.saveAll(finalList);
					
						
	}

}
