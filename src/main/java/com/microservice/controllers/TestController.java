package com.microservice.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.model.Employee;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class TestController {

	@RequestMapping(value = "/employee/1", method = RequestMethod.GET)
	public Employee revathi() {
		
		Employee emp = new Employee();
		emp.setName("revathi");
		emp.setDesignation("developer");
		emp.setEmpId("1");
		emp.setSalary(3000);
		
		if(emp.getName().equalsIgnoreCase("sid"))
			throw new RuntimeException();

		return emp;
	} 
	
	@RequestMapping(value = "/employee/2", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "getDataFallBack")
	public Employee sid() {
		
		System.out.println("******************Inside Original Method******************");


		Employee emp = new Employee();
		emp.setName("sid");
		emp.setDesignation("consultant");
		emp.setEmpId("2");
		emp.setSalary(5000);
		
		if(emp.getEmpId().equalsIgnoreCase("2"))
			throw new RuntimeException();

		return emp;
	}

	public Employee getDataFallBack() {
		
		System.out.println("//////////////////Inside FALLBACK Method\\\\\\\\\\\\\\\\\\\\\\");

		
		Employee emp = new Employee();
		emp.setName("fallback-emp1-name");
		emp.setDesignation("fallback-designation");
		emp.setEmpId("fallback-empid");
		emp.setSalary(0000);

		return emp;
	
	} 
}