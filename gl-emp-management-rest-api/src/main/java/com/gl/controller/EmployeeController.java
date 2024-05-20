package com.gl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.dto.EmployeeDto;
import com.gl.entity.Employee;
import com.gl.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping
	public Employee addNewEmployee(@RequestBody EmployeeDto empDto) {
		return employeeService.addNewEmployee(empDto);
	}
	
	@GetMapping
	public List<Employee> allEmployees(){
		return employeeService.allEmployees();
	}
	
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable("id") Integer id) {
			return employeeService.getEmployeeById(id);
	}
	
	@PutMapping("/{id}")
	public Employee updateEmployee(@PathVariable("id") Integer id, @RequestBody EmployeeDto empDto) {
		return employeeService.updateEmployee(id, empDto);
	}
	
	@DeleteMapping("/{id}")
	public String deleteEmployee(@PathVariable("id") Integer id) {
		return employeeService.deleteEmployee(id);
	}
	
	@GetMapping("/search/{firstName}")
	public List<Employee> getEmployeesByFirstName(@PathVariable("firstName") String firstName){
		return employeeService.getEmployeesByFirstName(firstName);
	}
	
	@GetMapping("/sort")
	public List<Employee> allEmployeesOrderByFirstName(@RequestParam("order") Direction order){
		return employeeService.allEmployeesOrderByFirstName(order);
	}

}
