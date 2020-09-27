package com.gcp.demo.controllers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gcp.demo.model.Employee;


@RestController
public class Controller {

	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public Employee firstPage() {

		Employee emp = new Employee();
		emp.setName("emp2");
		emp.setAge(23);
		emp.setEmpId("2");
		
		return emp;
	}

}
