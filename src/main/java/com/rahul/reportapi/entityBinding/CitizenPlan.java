package com.rahul.reportapi.entityBinding;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name= "CITIZENS_PLAN_INFO")
@Entity
public class CitizenPlan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cid;
	private String planName;
	private String planStatus;
	private String cname;
	private String cemail;
	private Long phno;
	private String gender;
	private Long ssn;
}
