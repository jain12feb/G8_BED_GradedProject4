package com.gl.service;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import com.gl.dto.EmployeeDto;
import com.gl.entity.Employee;

public interface EmployeeService {
	
	Employee addNewEmployee(EmployeeDto empDto);
	
	List<Employee> allEmployees();
	
	Employee getEmployeeById(Integer id);
	
	Employee updateEmployee(Integer id, EmployeeDto empDto);
	
	String deleteEmployee(Integer id);
	
	List<Employee> getEmployeesByFirstName(String firstName);
	
	List<Employee> allEmployeesOrderByFirstName(Direction order);

}
