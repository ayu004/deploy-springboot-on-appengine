package com.gcp.demo.test;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.gcp.demo.controllers.Controller;
import com.gcp.demo.model.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = Controller.class)
@EnableAutoConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ComponentScan("com.gcp.demo")
public class EmployeeControllerTests {

	@LocalServerPort
	int randomServerPort;
	
	private final static Logger log = LoggerFactory.getLogger(EmployeeControllerTests.class);
	
	TestRestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void test1_GetEmployees() throws URISyntaxException {
		
		final String baseUrl = "http://localhost:"+randomServerPort+"/api/v1/employees";
		
		URI uri = new URI(baseUrl);
		
		ResponseEntity<String> result = restTemplate.getForEntity(uri,String.class);
		log.info("Response for get Employees : "+result.getBody());
		
		Assert.assertEquals(200,result.getStatusCodeValue());
		Assert.assertEquals(true,result.getBody().equals("[{\"id\":100,\"firstName\":\"Suraj\",\"lastName\":\"Kumar\",\"emailId\":\"suraj-b.kumar@db.com\"},{\"id\":200,\"firstName\":\"Ayush\",\"lastName\":\"Sharma\",\"emailId\":\"ayush-a.sharma@db.com\"},{\"id\":300,\"firstName\":\"Sarath\",\"lastName\":\"Kaul\",\"emailId\":\"sarath.kaul@db.com\"}]"));
		
	}
	
	@Test
	public void test2_GetEmployeesById() throws URISyntaxException {
		
		final String baseUrl = "http://localhost:"+randomServerPort+"/api/v1/employees/100";
		
		URI uri = new URI(baseUrl);
		
		ResponseEntity<String> result = restTemplate.getForEntity(uri,String.class);
		log.info("Response for Employees Id=100 : "+result.getBody());
		
		Assert.assertEquals(200,result.getStatusCodeValue());
		Assert.assertEquals(true,result.getBody().equals("{\"id\":100,\"firstName\":\"Suraj\",\"lastName\":\"Kumar\",\"emailId\":\"suraj-b.kumar@db.com\"}"));
		
	}
	
	@Test
	public void test3_CreateEmployee() throws URISyntaxException {
		
		final String baseUrl = "http://localhost:"+randomServerPort+"/api/v1/employees";
		
		URI uri = new URI(baseUrl);
		
		Employee employee = new Employee();
		employee.setFirstName("admin");
		employee.setLastName("admin");
		employee.setEmailId("admin@gmail.com");
		
		ResponseEntity<Employee> postResponse = restTemplate.postForEntity(uri,employee, Employee.class);
		log.info("Response for Create Employee : "+postResponse.getBody());
		
		Assert.assertEquals(200,postResponse.getStatusCodeValue());
		Assert.assertNotNull(postResponse.getBody());
		
	}
	
	@Test
	public void test4_DeleteEmployee() throws URISyntaxException {
		
		final String baseUrl = "http://localhost:"+randomServerPort+"/api/v1/employees/300";
		final String getAllEmployeesBaseUrl = "http://localhost:"+randomServerPort+"/api/v1/employees";
		
		URI uri = new URI(baseUrl);

		restTemplate.delete(uri);
		
		ResponseEntity<String> result = restTemplate.getForEntity(getAllEmployeesBaseUrl, String.class);
		log.info("Response for Delete Employee id = 300  : "+result.getBody());
		
		Assert.assertEquals(200,result.getStatusCodeValue());
		Assert.assertEquals(false,result.getBody().contains(
				"{\"id\":300,\"firstName\":\"Sarath\",\"lastName\":\"Kaul\",\"emailId\":\"sarath.kaul@db.com\"}"
				));
		
	}

}